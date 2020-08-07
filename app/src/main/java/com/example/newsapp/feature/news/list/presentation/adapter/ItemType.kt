package com.example.newsapp.feature.news.list.presentation.adapter

import androidx.recyclerview.widget.RecyclerView


interface ItemType {

    var id: Long

    fun getItemViewType(): Int
    fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int)

    companion object {
        const val NEWS = 0
        const val RETRY = 1
    }
}
