package com.example.newsapp.feature.news.list.data

import com.example.newsapp.feature.news.domain.entity.News
import com.example.newsapp.feature.news.list.domain.ListRepository
import com.example.newsapp.feature.news.list.domain.entity.RawNews
import com.example.newsapp.feature.news.list.presentation.adapter.RetryItem

class ListRepositoryImpl(
    private val networkNewsDataSource: NetworkNewsDataSource,
    private val localNewsDataSource: LocalNewsDataSource
) : ListRepository {

    override suspend fun getNewsPage(page: Int): List<News> = networkNewsDataSource.getNewsPage(page)

}