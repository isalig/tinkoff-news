package io.aiico.news.feature.feed.di

import dagger.Component
import io.aiico.news.domain.usecase.GetArticlesListUseCase
import io.aiico.news.feature.feed.di.FeedComponent.FeedDependencies
import io.aiico.news.feature.feed.ui.FeedViewModel

@Component(dependencies = [FeedDependencies::class])
interface FeedComponent {

  val factory: FeedViewModel.Factory

  @Component.Factory
  interface Factory {
    fun create(dependencies: FeedDependencies): FeedComponent
  }

  companion object {
    fun create(dependencies: FeedDependencies): FeedComponent =
      DaggerFeedComponent.factory().create(dependencies)
  }

  interface FeedDependencies {
//    fun navigator(): NewsNavigator
    fun getArticlesUseCase(): GetArticlesListUseCase
  }
}
