package com.example.test10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.test10.databinding.ActivityMain288Binding
import com.example.test10.databinding.DialogInputBinding

class MainActivity288 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain288Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            // 사용자정의한 xml파일을 바인딩
            val dialogBinding = DialogInputBinding.inflate(layoutInflater)

            AlertDialog.Builder(this).run {
                setTitle("Input")
                setView(dialogBinding.root)
                setIcon(R.drawable.person1)
                dialogBinding.edit1.setOnClickListener{
                    Log.d("pjy","edit view 선택")
                    Toast.makeText(this@MainActivity288,"토스트 테스트", Toast.LENGTH_LONG).show()
                }
                setPositiveButton("닫기", null)
                show()
            }
        }
    }
}