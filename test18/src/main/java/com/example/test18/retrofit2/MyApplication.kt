package com.example.test18.retrofit2

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application() {
    // INetworkService
    // retrofit

    // retrofit2 통신 -> 위의 두 객체가 반드시 정의.
    var networkService: INetworkService

    // .baseUrl("https://reqres.in/")에서 백앤드 부분의 아이피 주소를 입력
    // ""안의 주소만 바꿔서 사용
    // 스프링 서버 연동하는 부분에서도 서버의 아이피 주소 입력
    // 아이피를 실제로 입력하는 것을 권장
    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            // json 타입의 값을 변환해주는 라이브러리
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    // 자동으로 호출
    init {
        networkService = retrofit.create(INetworkService::class.java)
    }
}