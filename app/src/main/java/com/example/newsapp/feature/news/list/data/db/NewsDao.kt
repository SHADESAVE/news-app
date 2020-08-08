package com.example.newsapp.feature.news.list.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.feature.news.list.domain.entity.News

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(news: List<News>)

    @Query("SELECT * FROM news_table WHERE news_page = :pageNum ORDER BY id ASC")
    fun getPage(pageNum: Int): PagingSource<Int, News>

    @Query("SELECT * FROM news_table")
    fun getPages(): PagingSource<Int, News>

    @Query("DELETE FROM news_table")
    suspend fun clearNews()
}