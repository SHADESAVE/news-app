package com.example.newsapp.feature.news.list.data

interface LocalNewsDataSource {

    suspend fun getNewsPage()
}

class LocalDataSourceImpl(

) : LocalNewsDataSource {

    override suspend fun getNewsPage() {
    }
}