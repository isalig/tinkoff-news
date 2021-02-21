package io.aiico.news.data

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ApiModule {

  @Singleton
  @Provides
  @JvmStatic
  fun provideArticlesApi() = ArticlesApiFactory.create()
}
