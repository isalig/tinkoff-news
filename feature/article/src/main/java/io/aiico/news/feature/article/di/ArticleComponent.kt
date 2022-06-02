package io.aiico.news.feature.article.di

import dagger.BindsInstance
import dagger.Component
import io.aiico.news.domain.usecase.GetArticleUseCase
import io.aiico.news.feature.article.di.ArticleComponent.ArticleDependencies
import io.aiico.news.feature.article.ui.ArticleViewModel

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

  interface ArticleDependencies {
    fun getArticleUseCase(): GetArticleUseCase
  }
}
