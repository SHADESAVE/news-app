package com.example.newsapp.app.di

import android.content.Context
import com.example.newsapp.feature.news.list.di.NewsDatabaseModule
import com.example.newsapp.feature.news.list.di.NewsModule
import com.example.newsapp.feature.news.list.di.NewsNetworkModule
import com.example.newsapp.feature.news.list.presentation.ListFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Scope

@Component(modules = [AppModule::class])
@AppScope
interface AppComponent {

	fun inject(fragment: ListFragment)

	@Component.Builder
	interface Builder {

		@BindsInstance
		fun context(context: Context): Builder

		@BindsInstance
		fun newsHost(newsHost: String): Builder

		fun build(): AppComponent
	}
}

@Module(
	includes = [
		NewsModule::class,
		NewsNetworkModule::class,
		NewsDatabaseModule::class
	]
)
interface AppModule

@Scope
annotation class AppScope