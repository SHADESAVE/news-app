package com.example.newsapp.feature.news.webview.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.feature.news.list.data.Api
import com.example.newsapp.feature.news.list.data.ListRepositoryImpl
import com.example.newsapp.feature.news.list.data.LocalDataSourceImpl
import com.example.newsapp.feature.news.list.data.NetworkDataSourceImpl
import com.example.newsapp.feature.news.list.presentation.ListViewModel
import com.example.newsapp.feature.news.webview.presentation.WebViewViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class WebViewViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass == WebViewViewModel::class.java) {
            return WebViewViewModel() as T
        } else {
            error("Unexpected class $modelClass")
        }
    }
}