package com.example.newsapp.feature.news.list.data

import com.example.newsapp.feature.news.domain.entity.News
import com.example.newsapp.feature.news.list.presentation.ListViewModel.Companion.LAST_PAGE
import com.example.newsapp.feature.news.list.presentation.ListViewModel.Companion.PAGE_SIZE
import com.example.newsapp.utils.toNews

interface NetworkNewsDataSource {

    suspend fun getNewsPage(page: Int): List<News>
}

class NetworkDataSourceImpl(
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