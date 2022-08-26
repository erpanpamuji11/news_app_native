package com.dicoding.news_app.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("top-headlines?token=5683651c974d448626795d6f847e2953")
    fun getListData(): Call<ApiResponse>
}