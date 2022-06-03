package io.aiico.tnews.presentation.di.module

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
object ApiModule {

  @Singleton
  @Provides
  @JvmStatic
  fun provideArticlesApi(client: OkHttpClient): io.aiico.news.shared.editorial.data.api.ArticlesApi = io.aiico.news.shared.editorial.data.ArticlesApiFactory.create(client)
}
