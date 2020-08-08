package com.example.newsapp.utils

import com.example.newsapp.feature.news.list.domain.entity.News
import com.example.newsapp.feature.news.list.domain.entity.Article

fun Article.toNews(id: Long, page: Int) =
    News(
        id = id,
        urlToArticle = this.url,
        urlToImage = this.urlToImage,
        newsSource = this.source.name,
        newsPage = page,
        title = this.title,
        description = this.description,
        publishDate = this.publishedAt
    )
