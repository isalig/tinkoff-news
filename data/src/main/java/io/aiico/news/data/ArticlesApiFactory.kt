package io.aiico.news.data

import io.aiico.news.data.api.ArticlesApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val API_BASE_URL = "https://cfg.tinkoff.ru/news/public/api/platform/v1/"

object ArticlesApiFactory {

  fun create(): ArticlesApi = provideArticlesApi(provideRetrofit())

  private fun provideRetrofit(): Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .baseUrl(API_BASE_URL)
    .build()

  private fun provideArticlesApi(retrofit: Retrofit): ArticlesApi = retrofit.create(ArticlesApi::class.java)
}
