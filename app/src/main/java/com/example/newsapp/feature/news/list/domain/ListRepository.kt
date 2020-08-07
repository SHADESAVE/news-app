package com.example.newsapp.feature.news.list.domain

import com.example.newsapp.feature.news.domain.entity.News
import com.example.newsapp.feature.news.list.presentation.adapter.RetryItem

interface ListRepository {

    suspend fun getNewsPage(page: Int): List<News>
}