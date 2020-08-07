package com.example.newsapp.feature.news.webview.presentation

import androidx.lifecycle.ViewModel
import com.example.newsapp.utils.viewmodel.SingleLiveEvent

class WebViewViewModel : ViewModel() {

    val newUrlEvent = SingleLiveEvent<String>()

    fun newUrl(url: String) {
        newUrlEvent(url)
    }
}