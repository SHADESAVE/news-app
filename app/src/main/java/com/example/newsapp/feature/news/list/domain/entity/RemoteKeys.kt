package com.example.newsapp.feature.news.list.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey
    val newsId: Long,
    val prevKey: Int?,
    val nextKey: Int?
)