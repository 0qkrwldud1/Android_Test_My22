package com.example.test13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test13.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            val intent: Intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("data1", "hello")
            intent.putExtra("data2", 10)
            startActivity(intent)
            // 메인에서 디테일으로 이동
        }
        val result = intent.getStringExtra("result")
        binding.result.text = "$result"
        Log.d("kk","$result")

        val result2 = intent.getStringExtra("id")
        val result3 = intent.getStringExtra("pw")
        binding.id.text = "$result2"
        binding.pw.text = "$result3"
        Log.d("kk","$result2")
        Log.d("kk","$result3")

    }
}