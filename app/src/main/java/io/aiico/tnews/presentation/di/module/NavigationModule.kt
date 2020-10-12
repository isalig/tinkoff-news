package io.aiico.tnews.presentation.di.module

import dagger.Module
import dagger.Provides
import io.aiico.tnews.presentation.navigation.NewsNavigator
import javax.inject.Singleton

@Module
object NavigationModule {

  @Singleton
  @JvmStatic
  @Provides
  fun provideNavigator() = NewsNavigator()
}
