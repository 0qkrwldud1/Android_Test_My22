package com.example.test13

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager

class MainActivity430 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main430)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            val controller = window.insetsController
            if (controller != null) {
                controller.hide(
                    WindowInsets.Type.statusBars() or
                        WindowInsets.Type.navigationBars())
                controller.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }
}
    /*

    싱글탑 예제
        액티비티가 화면에 출력되는 상황에서 똑같은 액태비티를 인텐트로 다시 실행
        알림이 대표적인 예 , 카카오 채팅
        카카오톡 채팅에서 알림메세지가 온것을 가정
        채팅화면이 chat Activity
        다른 친구의 메세지가 온경우 새메세지

        알림이 뜨면 터치 후 새채팅방으로 이동
        새채팅방 자체가 chatActivity가 되는 것

        스탠다드 -> 새 인텐트 객체를 계속 만듦
        싱글탑 모드-> 새로 생성 x

     */