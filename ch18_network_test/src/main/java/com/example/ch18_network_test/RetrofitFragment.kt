package com.example.ch18_network_test

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ch18_network_test.databinding.FragmentRetrofitBinding
import com.example.ch18_network_test.model.PageListModel
import com.example.ch18_network_test.recycler.MyAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RetrofitFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRetrofitBinding.inflate(inflater, container, false)


        // &returnType=json
        // &numOfRows=5
        // &pageNo=1
        // &stationName=종로구
        // &dataTerm=DAILY
        // &ver=1.0
        val call: Call<PageListModel> = MyApplication.networkService.getList(
            MyApplication.API_KEY,
            MyApplication.returnType,
            5,
            1,
            MyApplication.stationName,
            MyApplication.dataTerm,
            1.0
        )

        call?.enqueue(object: Callback<PageListModel>{
            override fun onResponse(call: Call<PageListModel>, response: Response<PageListModel>) {

                if(response.isSuccessful){
                    binding.retrofitRecyclerView.layoutManager = LinearLayoutManager(activity)
                    binding.retrofitRecyclerView.adapter = MyAdapter(activity as Context, response.body()?.items)
                }
            }

            override fun onFailure(call: Call<PageListModel>, t: Throwable) {
                Log.d("kkang", "error.......")
            }
        })


        return binding.root
    }


}