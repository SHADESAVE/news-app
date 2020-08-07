package com.example.newsapp.feature.news.list.domain

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.newsapp.feature.news.domain.entity.News
import com.example.newsapp.feature.news.list.presentation.adapter.ItemType
import kotlinx.coroutines.CoroutineScope

class PageDataSourceFactory(
    private val repository: ListRepository,
    private val coroutineScope: CoroutineScope
) : DataSource.Factory<Int, ItemType>() {

    val liveDataSource = MutableLiveData<ItemKeyedDataSource>()

    override fun create(): DataSource<Int, ItemType> {
        val source = ItemKeyedDataSource(repository, coroutineScope)
        liveDataSource.postValue(source)
        return source
    }
}