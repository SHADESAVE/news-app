package com.example.newsapp.feature

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.feature.news.list.data.Api
import com.example.newsapp.feature.news.list.data.ListRepositoryImpl
import com.example.newsapp.feature.news.list.data.NetworkDataSourceImpl
import com.example.newsapp.feature.news.list.data.db.NewsDatabase
import com.example.newsapp.feature.news.list.domain.ListRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Injection {

    private fun provideListRepository(context: Context): ListRepository {

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
        val database = NewsDatabase.getInstance(context)

        val networkDataSource = NetworkDataSourceImpl(api)

        return ListRepositoryImpl(networkDataSource, database)
    }

    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(
            provideListRepository(
                context
            )
        )
    }
}