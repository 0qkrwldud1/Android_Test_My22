package com.example.k1109_pjy_test

import android.app.Application
import com.example.k1109_pjy_test.retrofit.NetworkService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application() {

    // static
    companion object {
        val resultType = "json"
        val SERVICE_KEY = "vTKBxNEHPJ5tVm4/S2M65sJTJJxRlpWNIS0P+kU5II1z3I0U41eT8YFDDOKMiA6rkKFm/ovewSQnAK8LIS9kHg=="
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