package com.example.test13

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.example.test13.databinding.ActivityMain426Binding

class MainActivity426 : AppCompatActivity() {
    lateinit var binding: ActivityMain426Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMain426Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val manager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        binding.showInputButton.setOnClickListener {
            binding.editView.requestFocus()
            manager.showSoftInput(binding.editView, InputMethodManager.SHOW_IMPLICIT)
        }
        binding.hideInputButton.setOnClickListener {
            manager.hideSoftInputFromWindow(currentFocus?.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            // 화면에 기본 장식요소 툴바 등. 시스템창 경계안으로 들어가지 않도록 설정.
            // window.insetsController
            val controller = window.insetsController
            // 현재화면의 시스템 창에 대한 장식의 정보를 가져온다.
            // statusBars, navigationBars
            if (controller != null) {
                controller.hide(
                    // 숨기는 기능
                    WindowInsets.Type.statusBars() or
                            WindowInsets.Type.navigationBars())
                controller.systemBarsBehavior =
                    // 잠시 보이게 해준다.
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }
}