package com.example.newsapp.feature.news.list.domain

import android.util.Log
import androidx.paging.ItemKeyedDataSource
import com.example.newsapp.feature.news.domain.entity.News
import com.example.newsapp.feature.news.list.presentation.ListViewModel.Companion.PAGE_SIZE
import com.example.newsapp.feature.news.list.presentation.adapter.ItemType
import com.example.newsapp.feature.news.list.presentation.adapter.RetryItem
import com.example.newsapp.feature.news.list.presentation.adapter.RetryViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ItemKeyedDataSource(
    private val repository: ListRepository,
    private val coroutineScope: CoroutineScope
) : ItemKeyedDataSource<Int, ItemType>() {

    private var retryCheck = false
    private var lastPage = 0

    private var loadAfterCallback: LoadCallback<ItemType>? = null
    private var loadAfterParams: LoadParams<Int>? = null

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<ItemType>
    ) {
        coroutineScope.launch {
            try {
                callback.onResult(repository.getNewsPage(1))
            } catch (e: Exception) {
                throw error(e.toString())
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<ItemType>) {
        coroutineScope.launch {
            try {
                callback.onResult(repository.getNewsPage(params.key))
                retryCheck = false

            } catch (e: Exception) {

                loadAfterParams = params
                loadAfterCallback = callback

                if (!retryCheck) {
                    val newId = (lastPage - 1) * PAGE_SIZE + 1
                    callback.onResult(listOf(RetryItem(newId.toLong())))
                    retryCheck = true
                } else {
//                    callback.onResult(emptyList())
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<ItemType>) {
    }

    override fun getKey(item: ItemType): Int =
        when (item) {
            is News -> {
                var page = item.newsPage
                if (item.id.toInt() % 20 == 0) {
                    page++
                }
                lastPage = page
                lastPage
            }
            is RetryItem -> {
                lastPage
            }
            else -> {
                throw error("Unexpected item: $item")
            }
        }

    fun retry() {
        loadAfterParams?.let { loadParams ->
            loadAfterCallback?.let { loadCallback ->
                loadAfter(loadParams, loadCallback)
            }
        }
    }
}