package com.example.newsapp.app

import android.app.Application
import com.example.newsapp.app.di.AppComponent
import com.example.newsapp.app.di.DaggerAppComponent

class NewsApp : Application() {

	lateinit var appComponent: AppComponent

	override fun onCreate() {
		super.onCreate()
		appComponent = DaggerAppComponent.builder()
			.context(this)
			.newsHost("https://newsapi.org/")
			.build()
	}
}