package com.example.newsapp.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.feature.news.list.domain.ListRepository
import com.example.newsapp.feature.news.list.presentation.ListViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val repository: ListRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}