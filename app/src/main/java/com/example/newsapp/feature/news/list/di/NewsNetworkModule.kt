package com.example.newsapp.feature.news.list.di

import com.example.newsapp.app.di.AppScope
import com.example.newsapp.feature.news.list.data.Api
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NewsNetworkModule {

	@[Provides AppScope]
	fun provideApi(newsHost: String): Api {
		val interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

		val client = OkHttpClient.Builder()
			.addInterceptor(interceptor)
			.build()

		val retrofit = Retrofit.Builder()
			.baseUrl(newsHost)
			.addConverterFactory(GsonConverterFactory.create())
			.client(client)
			.build()

		return retrofit.create(Api::class.java)
	}
}