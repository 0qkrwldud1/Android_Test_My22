package com.example.test13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.test13.databinding.ActivityMain424Binding

class MainActivity424 : AppCompatActivity() {
    var count = 0
    lateinit var binding: ActivityMain424Binding

    var onCreateCount = 0
    var onRestoreCount = 0
    var onSaveCount = 0

    // 최초에 한번 호출
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMain424Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.plusCountButton.setOnClickListener {
            count++
            binding.countResultView.text="$count"
        }
        Log.d("kk","onCreate: $onCreateCount")
        onCreateCount++
    }

    override fun onStart() {
        super.onStart()
        Log.d("kk","onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.d("kk","onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.d("kk","onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.d("kk","onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("kk","onDestroy")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val data1 = savedInstanceState.getString("data1")
        val data2 = savedInstanceState.getInt("data2")
        val data3 = savedInstanceState.getInt("onSaveCount")
        val data4 = savedInstanceState.getInt("onRestoreCount")
        val edittext2 = savedInstanceState.getString("edittext")

        binding.editext.hint = "$edittext2"
        binding.countResultView.text="$data1 - $data2"
        binding.countsaveView.text = "$data3"
        Log.d("kk","onCreate: $onRestoreCount")
        onRestoreCount++
        binding.countrestore.text = "$data4"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("kkang","onSaveInstanceState..........")

        outState.putString("data1", "hello")
        outState.putInt("data2", 10)
        outState.putInt("onSaveCount", onSaveCount)
        outState.putInt("onRestoreCount", onRestoreCount)
        outState.putString("edittext", binding.editext.text.toString())

        Log.d("kk","onCreate: $onSaveCount")
        onSaveCount++
        binding.countsaveView.text = "$onSaveCount"
    }
}

//        번들 데이터 임시 저장
//        로그인 -> id, pw 입력 후
//        화면 회전 시 id, pw 값 그대로 유지