package com.example.newsapp.feature.news.list.di

import android.content.Context
import com.example.newsapp.app.di.AppScope
import com.example.newsapp.feature.news.list.data.db.NewsDao
import com.example.newsapp.feature.news.list.data.db.NewsDatabase
import com.example.newsapp.feature.news.list.data.db.RemoteKeysDao
import dagger.Module
import dagger.Provides

@Module
class NewsDatabaseModule {

	@[Provides AppScope]
	fun provideDatabase(context: Context): NewsDatabase = NewsDatabase.getInstance(context)

	@[Provides AppScope]
	fun provideMewsDao(database: NewsDatabase): NewsDao = database.newsDao()

	@[Provides AppScope]
	fun provideRemoteKeysDao(database: NewsDatabase): RemoteKeysDao = database.remoteKeysDao()
}