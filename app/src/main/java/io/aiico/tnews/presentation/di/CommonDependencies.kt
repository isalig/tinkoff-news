package io.aiico.tnews.presentation.di

import io.aiico.news.data.api.NewsApi
import io.aiico.tnews.presentation.navigation.NewsNavigator

interface CommonDependencies {

  fun provideNewsApi(): NewsApi

  fun provideNavigator(): NewsNavigator
}
