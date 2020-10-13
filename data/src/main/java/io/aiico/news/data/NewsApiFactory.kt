package io.aiico.news.data

import io.aiico.news.data.api.NewsApi
import io.aiico.news.data.api.RetrofitNewsApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val API_BASE_URL = "https://cfg.tinkoff.ru/news/public/api/platform/v1/"

object NewsApiFactory {

  fun create(): NewsApi = provideNewsApi(provideRetrofit())

  private fun provideRetrofit(): Retrofit =
    Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .baseUrl(API_BASE_URL)
      .build()

  private fun provideNewsApi(retrofit: Retrofit): RetrofitNewsApi =
    retrofit.create(RetrofitNewsApi::class.java)
}
