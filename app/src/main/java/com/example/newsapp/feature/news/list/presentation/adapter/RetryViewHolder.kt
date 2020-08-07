package com.example.newsapp.feature.news.list.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.news_list_retry_item.view.*

class RetryViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private val error = itemView.retry_text
    val button = itemView.retry_button

    fun bind(item: RetryItem) {

    }
}