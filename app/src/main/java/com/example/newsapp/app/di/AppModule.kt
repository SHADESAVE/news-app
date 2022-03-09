package com.example.newsapp.app.di

import com.example.newsapp.feature.news.list.di.NewsModule
import dagger.Component
import dagger.Module
import javax.inject.Scope

@Component(modules = [AppModule::class])
@AppScope
interface AppComponent

@Module(includes = [NewsModule::class])
interface AppModule

@Scope
annotation class AppScope