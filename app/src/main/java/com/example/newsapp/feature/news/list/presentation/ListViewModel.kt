package com.example.newsapp.feature.news.list.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.newsapp.feature.news.domain.entity.News
import com.example.newsapp.feature.news.list.domain.ListRepository
import com.example.newsapp.feature.news.list.domain.NewsPagingSource
import com.example.newsapp.feature.news.list.domain.PageDataSourceFactory
import com.example.newsapp.feature.news.list.presentation.adapter.RetryItem
import com.example.newsapp.utils.viewmodel.SingleLiveEvent

class ListViewModel(
    repository: ListRepository
) : ViewModel() {

    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = 20)
    ) {
        NewsPagingSource(repository)
    }.flow.cachedIn(viewModelScope)


    val newsClickEvent = SingleLiveEvent<News>()
    val retryClickEvent = SingleLiveEvent<Long>()

    private val factory = PageDataSourceFactory(repository, viewModelScope)
    private val config = PagedList.Config.Builder()
        .setPageSize(PAGE_SIZE)
        .setInitialLoadSizeHint(PAGE_SIZE)
        .setPrefetchDistance(PREFETCH_COUNT)
        .setEnablePlaceholders(false)
        .build()
    val news = LivePagedListBuilder(factory, config).build()

    fun newsItemClicked(news: News) {
        newsClickEvent(news)
    }


    fun retryButtonClicked(retryItem : RetryItem) {
        factory.liveDataSource.value?.retry()
//        retryClickEvent(retryItem.id)
    }

    companion object {
        const val PAGE_SIZE: Int = 20
        const val PREFETCH_COUNT: Int = 5
        const val LAST_PAGE: Int = 5
        const val START_PAGE: Int = 1
    }
}