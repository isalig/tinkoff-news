package io.aiico.news.data

import dagger.Module
import dagger.Provides
import io.aiico.news.data.api.NewsApi
import javax.inject.Singleton

@Module
object ApiModule {

  @Singleton
  @Provides
  @JvmStatic
  fun provideCurrencyApi(): NewsApi = NewsApiFactory.create()
}
