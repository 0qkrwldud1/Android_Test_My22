package com.example.test11

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test11.databinding.ActivityMain354Binding
import com.example.test11.databinding.Item354Binding

class MainActivity354 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main354)

        // 뷰 바인딩, 뷰페이저2가 기본화면, 데이터주입
        val binding= ActivityMain354Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 파이어 베이스에서 데이터 받아옴
        val datas = mutableListOf<String>()
        for(i in 1..3){
            datas.add("Item $i")
        }
        binding.viewpager.adapter=MyPagerAdapter(datas)
    }

    // 뷰 홀더
    // 뷰 객체들을 모아주는 역할
    // Item354Binding 바인딩 화면
    // 주 생성자의 매개변수에 (val binding: Item354Binding)
    class MyPagerViewHolder(val binding: Item354Binding): RecyclerView.ViewHolder(binding.root)

    // 어댑터
    // 뷰 객체에 데이터 바인딩
    // 주 생성자의 매개변수에 (val datas: MutableList<String>) -> 임의로 만든 리스트, 데이터를 받아올 예정
    class MyPagerAdapter(val datas: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        override fun getItemCount(): Int{
            return datas.size
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
                = MyPagerViewHolder(Item354Binding.inflate(LayoutInflater.from(parent.context), parent, false))

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            // MyPagerViewHolder의 뷰 ->
            // Item354Binding 안의 id가 ->
            // itemPagerTextView
            // 다시 바인딩해서 선택해서 사용함.
            val binding=(holder as MyPagerViewHolder).binding
            //뷰에 데이터 출력
            binding.itemPagerTextView.text=datas[position]
            // 포지션 -> 나머지
            when(position % 3){
                0 -> binding.itemPagerTextView.setBackgroundColor(Color.RED)
                1 -> binding.itemPagerTextView.setBackgroundColor(Color.BLUE)
                2 -> binding.itemPagerTextView.setBackgroundColor(Color.GREEN)
            }
        }
    }
}