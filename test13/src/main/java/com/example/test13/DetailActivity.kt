package com.example.test13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test13.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("kkang","DetailActivity..onCreate..........")
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 메인에서 넘어온 값 사용하는 부분.
        val data1 = intent.getStringExtra("data1")
        val data2 = intent.getIntExtra("data2", 0)

        binding.detailResultView.text = "data1: $data1, data2: $data2"

        binding.detailButton.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            intent.putExtra("result","world")
            setResult(RESULT_OK, intent)

            //startActivity(intent)
            // 디테일에서 한번 더 메인으로 이동

            finish()
            // 액티비티 종료 -> 이전 액티비티 이동
            // splash화면 구성에서 이용
        }
    }
}