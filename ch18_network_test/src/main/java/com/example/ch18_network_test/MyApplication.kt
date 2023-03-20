package com.example.ch18_network_test

import android.app.Application
import com.example.ch18_network_test.retrofit.NetworkService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application() {

    // static
    companion object {
        val returnType = "JSON"
        val stationName = "종로구"
        val dataTerm = "DAILY"
        val API_KEY = "vTKBxNEHPJ5tVm4%2FS2M65sJTJJxRlpWNIS0P%2BkU5II1z3I0U41eT8YFDDOKMiA6rkKFm%2FovewSQnAK8LIS9kHg%3D%3D"
        val BASE_URL = "https://apis.data.go.kr"
        val USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36"

        //add....................................
        var networkService: NetworkService
        val retrofit: Retrofit
            get() = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        init {
            networkService = retrofit.create(NetworkService::class.java)
        }
    }

}