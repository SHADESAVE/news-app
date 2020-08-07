package com.example.newsapp.feature.news.list.data

import com.example.newsapp.feature.news.list.domain.entity.RawNews
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/v2/everything?q=android&from=2019-04-00&sortBy=publishedAt&apiKey=26eddb253e7840f988aec61f2ece2907")
    suspend fun getNews(@Query("page") page: Int): RawNews
}