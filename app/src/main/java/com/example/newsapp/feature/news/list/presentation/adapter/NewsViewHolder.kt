package com.example.newsapp.feature.news.list.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.feature.news.domain.entity.News
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_list_news_item.view.*

class NewsViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private val image = itemView.news_image
    private val publishDate = itemView.news_publish_date
    private val tittle = itemView.news_tittle
    private val desc = itemView.news_desc

    fun bind(news: News) {
        Picasso.get()
            .load(news.urlToImage)
            .placeholder(R.drawable.loading_image_anim)
            .error(R.drawable.ic_broken_image)
            .into(image)
        tittle.text = news.title
        desc.text = news.description
        publishDate.text = news.publishDate
    }
}