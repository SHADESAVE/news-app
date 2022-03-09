package com.example.newsapp.app.di

import dagger.Component
import dagger.Module
import javax.inject.Scope

@Component(modules = [AppModule::class])
@AppScope
interface AppComponent

@Module
interface AppModule

@Scope
annotation class AppScope