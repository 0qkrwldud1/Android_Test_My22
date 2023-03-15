package com.example.test13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test13.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = binding.id.text
        val pw = binding.id.text

        binding.button1.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            intent.putExtra("id", "$id")
            intent.putExtra("pw", "$pw")
            startActivity(intent)
            // 메인에서 디테일으로 이동
        }
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
//        val binding = ActivityLoginBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val data1 = savedInstanceState.getString("id")
//        val data2 = savedInstanceState.getString("pw")
//
//        binding.id.hint="$data1"
//        binding.pw.hint = "$data2"

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
//        val binding = ActivityLoginBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        Log.d("kkang","onSaveInstanceState..........")
//
//        outState.putString("id", binding.id.text.toString())
//        outState.putString("pw", binding.pw.text.toString())
    }
}