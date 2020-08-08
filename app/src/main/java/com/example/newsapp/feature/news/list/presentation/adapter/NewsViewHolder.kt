package com.example.newsapp.feature.news.list.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.feature.news.list.domain.entity.News
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_list_news_item.view.*

class NewsViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(news: News) {
        itemView.news_title.text = news.title
        itemView.news_desc.text = news.description
        itemView.news_publish_date.text = news.publishDate

        if (!news.urlToImage.isNullOrEmpty()) {
            Picasso.get()
                .load(news.urlToImage)
                .placeholder(R.drawable.loading_image_anim)
                .error(R.drawable.ic_broken_image)
                .into(itemView.news_image)
        }
    }

    companion object {
        fun create(parent: ViewGroup): NewsViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.news_list_news_item, parent, false)
            return NewsViewHolder(view)
        }
    }
}