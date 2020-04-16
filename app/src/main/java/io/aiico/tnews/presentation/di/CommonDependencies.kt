package io.aiico.tnews.presentation.di

import io.aiico.tnews.data.api.NewsApi
import io.aiico.tnews.data.database.NewsDao
import io.aiico.tnews.presentation.navigation.NewsNavigator

interface CommonDependencies {

    fun provideNewsApi(): NewsApi

    fun provideNavigator(): NewsNavigator

    fun provideNewsDao(): NewsDao
}