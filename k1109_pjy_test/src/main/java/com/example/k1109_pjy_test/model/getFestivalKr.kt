package com.example.k1109_pjy_test.model

data class Festival (
    var getFestivalKr:GetFestivalKr
)

data class GetFestivalKr (
    var item:MutableList<Item>
)

data class Item (
    var TITLE: String?,
    var HOMEPAGE_URL: String?,
    var CNTCT_TEL: String?,
    var GUGUN_NM: String?,
    var MAIN_IMG_THUMB: String?
)