package com.example.newsapp.feature.news.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsapp.feature.news.list.domain.entity.News
import com.example.newsapp.feature.news.list.domain.ListRepository
import com.example.newsapp.utils.viewmodel.SingleLiveEvent

class ListViewModel(
    repository: ListRepository
) : ViewModel() {

    val newsClickEvent = SingleLiveEvent<News>()

    val result = repository.getResult().cachedIn(viewModelScope)

    fun newsItemClicked(news: News) {
        newsClickEvent(news)
    }
}