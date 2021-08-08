package io.aiico.tnews.presentation.list

import dagger.Component
import io.aiico.news.data.api.ArticlesApi
import io.aiico.tnews.presentation.di.module.DataModule
import io.aiico.tnews.presentation.list.FeedComponent.FeedDependencies
import io.aiico.tnews.presentation.navigation.NewsNavigator

@Component(
  modules = [DataModule::class],
  dependencies = [FeedDependencies::class]
)
interface FeedComponent {

  val presenter: FeedPresenter

  @Component.Factory
  interface Factory {
    fun create(dependencies: FeedDependencies): FeedComponent
  }

  companion object {
    fun create(dependencies: FeedDependencies): FeedComponent =
      DaggerFeedComponent.factory().create(dependencies)
  }

  interface FeedDependencies {
    fun navigator(): NewsNavigator
    fun articlesApi(): ArticlesApi
  }
}
