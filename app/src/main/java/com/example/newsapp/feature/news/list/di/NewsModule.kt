package com.example.newsapp.feature.news.list.di

import com.example.newsapp.app.di.AppScope
import com.example.newsapp.feature.news.list.data.ListRepositoryImpl
import com.example.newsapp.feature.news.list.data.NetworkDataSourceImpl
import com.example.newsapp.feature.news.list.data.NetworkNewsDataSource
import com.example.newsapp.feature.news.list.domain.ListRepository
import dagger.Binds
import dagger.Module

@Module
interface NewsModule {

	@[Binds AppScope]
	fun provideListRepository(repository: ListRepositoryImpl): ListRepository

	@[Binds AppScope]
	fun provideNetworkNewsDataSource(datasource: NetworkDataSourceImpl): NetworkNewsDataSource
}