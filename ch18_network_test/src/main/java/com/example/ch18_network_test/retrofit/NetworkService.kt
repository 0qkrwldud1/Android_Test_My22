package com.example.ch18_network_test.retrofit

import com.example.ch18_network_test.model.PageListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    // &returnType=json
    // &numOfRows=5
    // &pageNo=1
    // &stationName=종로구
    // &dataTerm=DAILY
    // &ver=1.0

    @GET("/B552584/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty")
    fun getList(
        @Query("serviceKey") serviceKey: String?,
        @Query("returnType") returnType: String?,
        @Query("numOfRows") numOfRows: Int,
        @Query("pageNo") pageNo: Int,
        @Query("stationName") stationName: String?,
        @Query("dataTerm") dataTerm: String?,
        @Query("ver") ver: Double
    ): Call<PageListModel>
}