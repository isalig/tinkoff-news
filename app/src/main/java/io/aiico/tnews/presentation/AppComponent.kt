package io.aiico.tnews.presentation

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import io.aiico.news.data.ApiModule
import io.aiico.tnews.presentation.detailed.ArticleComponent.ArticleDependencies
import io.aiico.tnews.presentation.di.module.AppModule
import io.aiico.tnews.presentation.di.module.DataModule
import io.aiico.tnews.presentation.di.module.NavigationModule
import io.aiico.tnews.presentation.list.FeedComponent.FeedDependencies
import io.aiico.tnews.presentation.navigation.NewsNavigator
import okhttp3.Interceptor
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    AppModule::class,
    ApiModule::class,
    NavigationModule::class,
    DataModule::class
  ]
)
interface AppComponent : FeedDependencies, ArticleDependencies {

  val newsNavigator: NewsNavigator

  @Component.Factory
  interface Factory {
    fun create(
      @BindsInstance context: Context,
      @BindsInstance interceptor: Interceptor
    ): AppComponent
  }

  companion object {
    fun create(context: Context, networkInterceptor: Interceptor): AppComponent =
      DaggerAppComponent.factory().create(context, networkInterceptor)
  }
}