package io.aiico.news.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.aiico.news.data.api.ArticlesApi
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BASIC
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

private const val API_BASE_URL = "https://cfg.tinkoff.ru/about/public/api/news/platform/v1/"

object ArticlesApiFactory {

  fun create(): ArticlesApi = provideArticlesApi(provideRetrofit())

  @OptIn(ExperimentalSerializationApi::class)
  private fun provideRetrofit(): Retrofit = Retrofit.Builder()
    .client(provideOkHttpClient())
    .addConverterFactory(provideJson().asConverterFactory("application/json".toMediaType()))
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .baseUrl(API_BASE_URL)
    .build()

  private fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
      .addInterceptor(provideLoggingInterceptor())
      .build()

  private fun provideLoggingInterceptor() = HttpLoggingInterceptor().setLevel(BASIC)

  private fun provideJson() = Json { ignoreUnknownKeys = true }

  private fun provideArticlesApi(retrofit: Retrofit): ArticlesApi = retrofit.create(ArticlesApi::class.java)
}
