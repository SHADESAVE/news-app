package com.example.newsapp.feature.news.list.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsapp.feature.news.list.domain.entity.News
import com.example.newsapp.feature.news.list.data.db.NewsDatabase
import com.example.newsapp.feature.news.list.domain.ListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListRepositoryImpl @Inject constructor(
    private val networkNewsDataSource: NetworkNewsDataSource,
    private val database: NewsDatabase
) : ListRepository {

    override fun getResult(): Flow<PagingData<News>> {


        val pagingSourceFactory = { database.newsDao().getPages() }
        return Pager(
            PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = PREFETCH_COUNT,
                enablePlaceholders = false
                // initialLoadSize = 1 — Могут возникнуть проблемы если не использовать стандартный сайз = 3
                // https://github.com/googlecodelabs/android-paging/issues/79
            ),
            remoteMediator = NewsRemoteMediator(
                networkNewsDataSource,
                database
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    companion object {
        const val PAGE_SIZE: Int = 20
        const val PREFETCH_COUNT: Int = 5
        const val START_PAGE: Int = 1
        const val LAST_PAGE: Int = 5
    }
}