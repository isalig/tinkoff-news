package io.aiico.news.data

import dagger.Module
import dagger.Provides
import io.aiico.news.data.api.NewsApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object ApiModule {

  @Singleton
  @Provides
  @JvmStatic
  fun provideCurrencyApi(): NewsApi = NewsApiFactory.create()
}
