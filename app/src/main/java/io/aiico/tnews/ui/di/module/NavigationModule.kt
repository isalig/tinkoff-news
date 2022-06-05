package io.aiico.tnews.ui.di.module

import dagger.Binds
import dagger.Module
import dagger.Provides
import io.aiico.news.feature.feed.navigation.FeedRouter
import io.aiico.tnews.ui.navigation.NewsNavigator
import javax.inject.Singleton

@Module
interface NavigationModule {

  @Binds
  fun bindFeedRouter(impl: NewsNavigator): FeedRouter

  @Module
  companion object {
    @Singleton
    @JvmStatic
    @Provides
    fun provideNavigator() = NewsNavigator()
  }
}
