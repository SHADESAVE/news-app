package com.example.newsapp.feature.news.list.domain

import androidx.paging.PagingSource
import com.example.newsapp.feature.news.domain.entity.News
import com.example.newsapp.feature.news.list.presentation.ListViewModel.Companion.START_PAGE
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

class NewsPagingSource(
    private val repository: ListRepository
) : PagingSource<Int, News>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {
        val nextPageNumber = params.key ?: START_PAGE
        return try {
            val response = repository.getNewsPage(nextPageNumber)
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = if (response.isEmpty()) null else nextPageNumber + 1
            )
        } catch (e: IOException) {
            // IOException for network failures.
            LoadResult.Error(e)
        } catch (e: HttpException) {
            // HttpException for any non-2xx HTTP status codes.
            LoadResult.Error(e)
        }
    }
}