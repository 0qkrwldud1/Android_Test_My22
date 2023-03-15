package com.example.test13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.test13.databinding.ActivityMain406Binding
import com.example.test13.databinding.ActivityMain408Binding

class MainActivity408 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain408Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 후처리
        val requestLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult())
        {
            // it : 디테일에서 넘어온 데이터 intent
            val resultData = it.data?.getStringExtra("result")
            binding.mainResultView.text = "result : $resultData"
        }

        binding.button1.setOnClickListener {
            // 사용
            val intent: Intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("data1", "hello")
            intent.putExtra("data2", 10)
            requestLauncher.launch(intent)
        }
    }
}