package com.example.newsapp.feature.news.list.data

import com.example.newsapp.feature.news.list.domain.entity.News
import com.example.newsapp.feature.news.list.data.ListRepositoryImpl.Companion.LAST_PAGE
import com.example.newsapp.feature.news.list.data.ListRepositoryImpl.Companion.PAGE_SIZE
import com.example.newsapp.utils.toNews
import javax.inject.Inject

interface NetworkNewsDataSource {

    suspend fun getNewsPage(page: Int): List<News>
}

class NetworkDataSourceImpl @Inject constructor(
    private val api: Api
) : NetworkNewsDataSource {

    override suspend fun getNewsPage(page: Int): List<News> {

        return if (page > LAST_PAGE) {
            emptyList()
        } else {
            var counterId: Long = PAGE_SIZE * (page - 1).toLong()
            api.getNews(page).articles.map {
                counterId++
                it.toNews(counterId, page)
            }
        }
    }
}