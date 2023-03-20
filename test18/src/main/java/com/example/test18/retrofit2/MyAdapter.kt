package com.example.test18.retrofit2

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.test18.databinding.ItemRetrofitBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewHolder(val binding: ItemRetrofitBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter(val context: Context, val datas: List<UserModel>?): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun getItemCount(): Int{
        return datas?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = MyViewHolder(ItemRetrofitBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val binding=(holder as MyViewHolder).binding
        val user = datas?.get(position)
        // datas ->  <UserListModel> -> var data = list <UserModel>
        binding.id.text=user?.id
        binding.firstNameView.text=user?.firstName
        binding.lastNameView.text=user?.lastName

        // avatar 객체 존재 -> 이미지 url주소가 있다면
        user?.avatar?.let {
            val avatarImageCall = (context.applicationContext as MyApplication).networkService.getAvatarImage(it)
            // 아바타를 가져오는 통신 호출 부분
            // MyApplication -> 직접 만든 인터페이스의 객체, retrofit 객체
            // 해당 인터페이스의 함수 호출
            // 이미지 가져오는 추상 메서드
            // Call타입 반환
            // it -> 아바타의 url주소
            Log.d("kk","url주소 : $it")
            avatarImageCall.enqueue(object : Callback<ResponseBody> {
                // 실제 작업은 enqueue()호출되는 순간부터 통신 시작
                // 응답을 responce객체에 담아서 전달을 서버로 부터 받는다
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            // 이미지만 전문으로 처리하는 ,Glide라는 라이브러리 사용
                            // 스트림으로 읽어서 비트맵으로 변환해서 출력하는 예제
//                            val bitmap = BitmapFactory.decodeStream(response.body()!!.byteStream())
//                            binding.avatarView.setImageBitmap(bitmap)

                                //Glide 사진처리
                                Glide.with(context)
                                .asBitmap()
                                .load(it)
                                .into(object : CustomTarget<Bitmap>(100, 100) {
                                    override fun onResourceReady(
                                        resource: Bitmap,
                                        transition: Transition<in Bitmap>?
                                    ) {
                                        binding.avatarView.setImageBitmap(resource)
                                        Log.d("kkang", "width : ${resource.width}, height: ${resource.height}")
                                    }

                                    override fun onLoadCleared(placeholder: Drawable?) {
                                        TODO("Not yet implemented")
                                    }
                                })
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    call.cancel()
                }
            })
        }



    }

}
