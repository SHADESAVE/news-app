package com.example.newsapp.feature.news.list.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.newsapp.feature.news.list.domain.entity.News
import com.example.newsapp.feature.news.list.domain.entity.RemoteKeys
import com.example.newsapp.feature.news.list.data.ListRepositoryImpl.Companion.START_PAGE
import com.example.newsapp.feature.news.list.data.db.NewsDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.single
import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException

@OptIn(ExperimentalPagingApi::class)
class NewsRemoteMediator(
    private val networkNewsDataSource: NetworkNewsDataSource,
    private val newsDatabase: NewsDatabase
) : RemoteMediator<Int, News>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, News>): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: START_PAGE
            }

            LoadType.PREPEND -> {
                val remoteKeys = flowOf(getRemoteKeyForFirstItem(state))
                    .flowOn(Dispatchers.IO).single()
                    ?: throw InvalidObjectException("Remote key and the prevKey should not be null")

                remoteKeys.prevKey ?: return MediatorResult.Success(endOfPaginationReached = true)
                remoteKeys.prevKey
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                if (remoteKeys?.nextKey == null) {
                    throw InvalidObjectException("Remote key should not be null for $loadType")
                }
                remoteKeys.nextKey
            }
        }

        return try {

            val newsList = networkNewsDataSource.getNewsPage(page)

            val prevKey = if (page == START_PAGE) null else page - 1
            val nextKey = if (newsList.isEmpty()) null else page + 1

            val endOfPaginationReached = newsList.isEmpty()
            newsDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    newsDatabase.remoteKeysDao().clearRemoteKeys()
                    newsDatabase.newsDao().clearNews()
                }

                val keys = newsList.map {
                    RemoteKeys(
                        newsId = it.id,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                }

                newsDatabase.remoteKeysDao().insertAll(keys)
                newsDatabase.newsDao().insertAll(newsList)
            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, News>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let {
                newsDatabase.remoteKeysDao().remoteKeysRepoId(it)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, News>): RemoteKeys? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()
            ?.let {
                newsDatabase.remoteKeysDao().remoteKeysRepoId(it.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, News>): RemoteKeys? {
        return state.pages.lastOrNull() {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()
            ?.let {
                newsDatabase.remoteKeysDao().remoteKeysRepoId(it.id)
            }
    }
}