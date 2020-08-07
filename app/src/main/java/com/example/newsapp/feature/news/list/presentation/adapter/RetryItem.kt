package com.example.newsapp.feature.news.list.presentation.adapter

import androidx.recyclerview.widget.RecyclerView

data class RetryItem(
    override var id: Long = 0
): ItemType {
    override fun getItemViewType(): Int = ItemType.RETRY

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        (viewHolder as RetryViewHolder).bind(this)
    }

}