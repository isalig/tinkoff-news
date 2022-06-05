package io.aiico.news.feature.article.di

import dagger.BindsInstance
import dagger.Component
import io.aiico.news.feature.article.di.ArticleComponent.ArticleDependencies
import io.aiico.news.feature.article.ui.ArticleViewModel
import io.aiico.news.shared.di.Dependencies
import io.aiico.news.shared.editorial.domain.usecase.GetArticleUseCase

@Component(dependencies = [ArticleDependencies::class])
interface ArticleComponent {

  val factory: ArticleViewModel.Factory

  @Component.Factory
  interface Factory {
    fun create(@BindsInstance id: String, dependencies: ArticleDependencies): ArticleComponent
  }

  companion object {

    fun create(newsId: String, dependencies: ArticleDependencies): ArticleComponent =
      DaggerArticleComponent.factory().create(newsId, dependencies)
  }

  interface ArticleDependencies : Dependencies {
    fun getArticleUseCase(): GetArticleUseCase
  }
}
