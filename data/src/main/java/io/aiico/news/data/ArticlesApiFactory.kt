package io.aiico.news.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.aiico.news.data.api.ArticlesApi
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

private const val API_BASE_URL = "https://cfg.tinkoff.ru/about/public/api/news/platform/v1/"

object ArticlesApiFactory {

  fun create(client: OkHttpClient): ArticlesApi = provideArticlesApi(provideRetrofit(client))

  @OptIn(ExperimentalSerializationApi::class)
  private fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
    .client(client)
    .addConverterFactory(provideJson().asConverterFactory("application/json".toMediaType()))
    .baseUrl(API_BASE_URL)
    .build()

  private fun provideJson() = Json { ignoreUnknownKeys = true }

  private fun provideArticlesApi(retrofit: Retrofit): ArticlesApi = retrofit.create(ArticlesApi::class.java)
}
