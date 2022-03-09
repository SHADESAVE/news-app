package com.example.newsapp.app.di

import com.example.newsapp.feature.news.list.di.NewsModule
import com.example.newsapp.feature.news.list.di.NewsNetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Scope

@Component(modules = [AppModule::class])
@AppScope
interface AppComponent {

	@Component.Builder
	interface Builder {

		@BindsInstance
		fun newsHost(newsHost: String): Builder

		fun build(): AppComponent
	}
}

@Module(includes = [NewsModule::class, NewsNetworkModule::class])
interface AppModule

@Scope
annotation class AppScope