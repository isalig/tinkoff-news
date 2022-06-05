package io.aiico.tnews.ui.di.module

import dagger.Module
import dagger.Provides
import io.aiico.news.shared.editorial.data.ArticlesApiFactory
import io.aiico.news.shared.editorial.data.api.ArticlesApi
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
object ApiModule {

  @Singleton
  @Provides
  @JvmStatic
  fun provideArticlesApi(client: OkHttpClient): ArticlesApi = ArticlesApiFactory.create(client)
}
