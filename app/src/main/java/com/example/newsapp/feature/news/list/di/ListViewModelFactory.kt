package com.example.newsapp.feature.news.list.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.feature.news.list.data.Api
import com.example.newsapp.feature.news.list.data.ListRepositoryImpl
import com.example.newsapp.feature.news.list.data.LocalDataSourceImpl
import com.example.newsapp.feature.news.list.data.NetworkDataSourceImpl
import com.example.newsapp.feature.news.list.presentation.ListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ListViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass == ListViewModel::class.java) {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            val api = retrofit.create(Api::class.java)

            val networkDataSource = NetworkDataSourceImpl(api)
            val localNewsDataSource = LocalDataSourceImpl()
            val cityRepository = ListRepositoryImpl(networkDataSource, localNewsDataSource)

            return ListViewModel(cityRepository) as T
        } else {
            error("Unexpected class $modelClass")
        }
    }
}