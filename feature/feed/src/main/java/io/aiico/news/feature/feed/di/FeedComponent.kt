package io.aiico.news.feature.feed.di

import dagger.Component
import io.aiico.news.feature.feed.di.FeedComponent.FeedDependencies
import io.aiico.news.feature.feed.navigation.FeedRouter
import io.aiico.news.feature.feed.ui.FeedViewModel
import io.aiico.news.shared.di.Dependencies
import io.aiico.news.shared.editorial.domain.usecase.GetArticlesListUseCase

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

  interface FeedDependencies : Dependencies {
    val getArticlesUseCase: GetArticlesListUseCase
    val feedRouter: FeedRouter
  }
}
