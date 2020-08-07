package com.example.newsapp.feature.news.list.domain.entity

data class RawNews(
    val status: String? = null,
    val totalResults: Int? = null,
    val articles: List<Article>
)

data class Article(
    val source: Source,
    val author: String? = null,
    val title: String? = null,
    val description: String? = null,
    val url: String,
    val urlToImage: String? = null,
    val publishedAt: String? = null,
    val content: String? = null
)

data class Source(
    val id: String? = null,
    val name: String? = null
)