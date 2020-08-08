package com.example.newsapp.feature.news.list.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_table")
data class News(

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Long = 0,

    @ColumnInfo(name = "url_to_article")
    var urlToArticle: String = "",

    @ColumnInfo(name = "url_to_image")
    var urlToImage: String? = "",

    @ColumnInfo(name = "news_source")
    var newsSource: String? = "",

    @ColumnInfo(name = "news_page")
    var newsPage: Int = 1,

    @ColumnInfo(name = "title")
    var title: String? = "",

    @ColumnInfo(name = "description")
    var description: String? = "",

    @ColumnInfo(name = "publish_date")
    var publishDate: String? = ""
)