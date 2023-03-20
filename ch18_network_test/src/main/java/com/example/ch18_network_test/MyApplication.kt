package com.example.ch18_network_test

import android.app.Application
import com.example.ch18_network_test.retrofit.NetworkService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application() {

    // static
    companion object {
        val resultType = "json"
        val API_KEY = "vTKBxNEHPJ5tVm4%2FS2M65sJTJJxRlpWNIS0P%2BkU5II1z3I0U41eT8YFDDOKMiA6rkKFm%2FovewSQnAK8LIS9kHg%3D%3D"
        val BASE_URL = "https://apis.data.go.kr"

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