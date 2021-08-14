package io.aiico.tnews.presentation.feed

import dagger.Component
import io.aiico.news.domain.usecase.GetArticlesListUseCase
import io.aiico.tnews.presentation.feed.FeedComponent.FeedDependencies
import io.aiico.tnews.presentation.navigation.NewsNavigator

@Component(dependencies = [FeedDependencies::class])
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
    fun getArticlesUseCase(): GetArticlesListUseCase
  }
}
