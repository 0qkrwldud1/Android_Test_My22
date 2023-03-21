package com.example.k1109_pjy_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.k1109_pjy_test.databinding.ActivityMainBinding
import com.example.k1109_pjy_test.model.Festival
import com.example.k1109_pjy_test.recycler.MyAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val networkService = MyApplication.networkService

        val userListCall = networkService.getList(
           MyApplication.SERVICE_KEY,
            1,
            100,
            MyApplication.resultType
        )

        userListCall.enqueue(object : Callback<Festival> {
            override fun onResponse(call: Call<Festival>, response: Response<Festival>) {

                val userList = response.body()
                //.......................................

                binding.recyclerView.adapter= MyAdapter(this@MainActivity, userList?.getFestivalKr?.item)

                binding.recyclerView.addItemDecoration(
                    DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL)
                )

            }

            override fun onFailure(call: Call<Festival>, t: Throwable) {
                call.cancel()
            }
        })



    }
}