package io.aiico.tnews.presentation.di.module

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
object AppModule {

  @Provides
  @Singleton
  @JvmStatic
  fun provideOkHttpClient(networkInterceptor: Interceptor?): OkHttpClient =
    OkHttpClient.Builder().apply {
      addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
      networkInterceptor?.let(::addNetworkInterceptor)
    }.build()
}
