package com.example.ch18_network_test.retrofit

import com.example.ch18_network_test.model.PageListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("/6260000/FestivalService/getFestivalKr")
    fun getList(
        @Query("serviceKey") serviceKey: String?,
        @Query("pageNo") pageNo: Int,
        @Query("numOfRows") numOfRows: Int,
        @Query("resultType") resultType: String?
    ): Call<PageListModel>
}