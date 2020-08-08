package com.example.newsapp.feature.news.webview.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.feature.news.webview.presentation.WebViewViewModel

class WebViewViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass == WebViewViewModel::class.java) {
            return WebViewViewModel() as T
        } else {
            error("Unexpected class $modelClass")
        }
    }
}