package com.example.test13

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.test13.databinding.ActivityMain406Binding

class MainActivity406 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain406Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            val intent: Intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("data1", "hello")
            intent.putExtra("data2", 10)
            // 후처리
            // startActivity() 후처리 안할때
            startActivityForResult(intent, 10)
        }
    }
    // 디테일에서 넘어온 값 처리하는 코드
    // 자동으로 실행
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val binding = ActivityMain406Binding.inflate(layoutInflater)
        setContentView(binding.root)
        if (requestCode == 10 && resultCode == Activity.RESULT_OK) {
            val result = data?.getStringExtra("result")
            Toast.makeText(this@MainActivity406,"$result",Toast.LENGTH_LONG).show()
            binding.result.text = "$result"
        }
    }
}

//        splash, 로그인, 로그인 후
//        splash 이미지 3초
//        로그인 이동 -> id, pw , 버튼 클릭 시 로그인 후 화면: 메인
//        로그인 후 메인, id pw 값을 뷰에 보이게