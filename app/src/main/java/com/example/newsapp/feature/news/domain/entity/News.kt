package com.example.newsapp.feature.news.domain.entity

import androidx.recyclerview.widget.RecyclerView
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp.feature.news.list.presentation.adapter.ItemType
import com.example.newsapp.feature.news.list.presentation.adapter.NewsViewHolder

@Entity(tableName = "news_table")
data class News(
    @PrimaryKey
    @ColumnInfo(name = "id")
    override var id: Long = 0,

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
) : ItemType {

    override fun getItemViewType(): Int = ItemType.NEWS

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        (viewHolder as NewsViewHolder).bind(this)
    }
}