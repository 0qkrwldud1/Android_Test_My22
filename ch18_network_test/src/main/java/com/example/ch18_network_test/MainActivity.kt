package com.example.ch18_network_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ch18_network_test.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.retrofitRecyclerView, RetrofitFragment())
            .commit()
    }
}