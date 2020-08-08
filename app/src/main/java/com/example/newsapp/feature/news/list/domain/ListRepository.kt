package com.example.newsapp.feature.news.list.domain

import androidx.paging.PagingData
import com.example.newsapp.feature.news.list.domain.entity.News
import kotlinx.coroutines.flow.Flow

interface ListRepository {

    fun getResult(): Flow<PagingData<News>>
}