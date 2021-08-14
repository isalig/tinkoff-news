package io.aiico.tnews.presentation.article

import dagger.BindsInstance
import dagger.Component
import io.aiico.news.domain.usecase.GetArticleUseCase
import io.aiico.tnews.presentation.article.ArticleComponent.ArticleDependencies

@Component(dependencies = [ArticleDependencies::class])
interface ArticleComponent {

  val presenter: ArticlePresenter

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
