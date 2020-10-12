package io.aiico.tnews.presentation.di.module

import dagger.Module
import dagger.Provides
import io.aiico.tnews.BuildConfig
import io.aiico.tnews.data.api.NewsApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object ApiModule {

  @Provides
  @JvmStatic
  fun provideRetrofit(): Retrofit =
    Retrofit.Builder()
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .baseUrl(BuildConfig.API_BASE_URL)
      .build()

  @Singleton
  @Provides
  @JvmStatic
  fun provideCurrencyApi(retrofit: Retrofit): NewsApi =
    retrofit.create(NewsApi::class.java)
}
