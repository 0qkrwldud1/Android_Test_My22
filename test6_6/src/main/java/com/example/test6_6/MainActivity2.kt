package com.example.test6_6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.test6_6.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val binding = ActivityMain2Binding.inflate(layoutInflater)
        // 액티비티 화면 출력
        setContentView(binding.root)

        val rid : Int = R.id.text1

        // 뷰 객체이용
        binding.image2.setOnClickListener{
            Log.d("click", "버튼클릭테스트중")
            binding.image2.visibility= View.INVISIBLE
        }
    }
}

private fun ImageView.setOnClickListener(visible: Unit) {

}
