package io.aiico.tnews.presentation.di.module

import dagger.Module
import dagger.Provides
import io.aiico.news.data.ArticlesApiFactory
import io.aiico.news.data.api.ArticlesApi
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
object ApiModule {

  @Singleton
  @Provides
  @JvmStatic
  fun provideArticlesApi(client: OkHttpClient): ArticlesApi = ArticlesApiFactory.create(client)
}
