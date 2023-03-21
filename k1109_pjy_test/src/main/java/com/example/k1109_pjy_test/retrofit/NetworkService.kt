package com.example.k1109_pjy_test.retrofit

import com.example.k1109_pjy_test.model.Festival
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {

    @GET("/6260000/FestivalService/getFestivalKr")
    fun getList(
        @Query("serviceKey") serviceKey: String?,
        @Query("pageNo") pageNo: Int,
        @Query("numOfRows") numOfRows: Int,
        @Query("resultType") resultType: String?
    ): Call<Festival>
}