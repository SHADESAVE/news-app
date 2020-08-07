package com.example.newsapp.feature.news.list.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.feature.news.domain.entity.News

class ListAdapter(
    private val itemClickListener: (News) -> Unit,
    private val retryClickListener: (RetryItem) -> Unit
) : PagedListAdapter<ItemType, RecyclerView.ViewHolder>(NewsDiffCallback) {

    override fun getItemViewType(position: Int): Int =
        getItem(position)?.getItemViewType()!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder = getViewHolder(parent, viewType)
        setupClickListeners(holder)
        return holder
    }

    override fun onCurrentListChanged(
        previousList: PagedList<ItemType>?,
        currentList: PagedList<ItemType>?
    ) {
        if (previousList != null && previousList.contains(RetryItem(21))) {
            currentList?.remove(RetryItem(21))
        }
        super.onCurrentListChanged(previousList, currentList)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.onBindViewHolder(holder, position)
    }

    private fun getViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            ItemType.NEWS -> {
                NewsViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.news_list_news_item, parent, false)
                )
            }
            ItemType.RETRY -> {
                RetryViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.news_list_retry_item, parent, false)
                )
            }
            else -> throw error("ListAdapter. Unexpected itemType: $viewType")
        }

    private fun setupClickListeners(holder: RecyclerView.ViewHolder) {
        when (holder) {
            is NewsViewHolder -> holder.itemView.setOnClickListener {
                getItem(holder.adapterPosition)?.let {
                    itemClickListener(it as News)
                }
            }
            is RetryViewHolder -> holder.button.setOnClickListener {
                getItem(holder.adapterPosition)?.let {
                    retryClickListener(it as RetryItem)
                }
            }
            else -> throw error("ListAdapter. Unexpected holder: $holder")
        }
    }

    companion object {
        val NewsDiffCallback = object : DiffUtil.ItemCallback<ItemType>() {
            override fun areItemsTheSame(oldItem: ItemType, newItem: ItemType) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ItemType, newItem: ItemType): Boolean =
                oldItem is News && newItem is News && oldItem.urlToArticle == newItem.urlToArticle
        }
    }
}