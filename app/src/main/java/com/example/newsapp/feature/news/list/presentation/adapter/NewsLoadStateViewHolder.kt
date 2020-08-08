package com.example.newsapp.feature.news.list.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import kotlinx.android.synthetic.main.news_load_state_footer_view_item.view.*

class NewsLoadStateViewHolder(
    itemView: View,
    retry: () -> Unit
) : RecyclerView.ViewHolder(itemView) {

    init {
        itemView.retry_button.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            itemView.error_msg.text = loadState.error.localizedMessage
        }
        itemView.progress_bar.isVisible = loadState is LoadState.Loading
        itemView.retry_button.isVisible = loadState !is LoadState.Loading
        itemView.error_msg.isVisible = loadState !is LoadState.Loading
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): NewsLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.news_load_state_footer_view_item, parent, false)
            return NewsLoadStateViewHolder(view, retry)
        }
    }
}