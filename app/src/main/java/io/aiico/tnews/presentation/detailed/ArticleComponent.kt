package io.aiico.tnews.presentation.detailed

import dagger.BindsInstance
import dagger.Component
import io.aiico.news.data.api.ArticlesApi
import io.aiico.tnews.presentation.detailed.ArticleComponent.ArticleDependencies
import io.aiico.tnews.presentation.di.module.DataModule

@Component(
  modules = [DataModule::class],
  dependencies = [ArticleDependencies::class]
)
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
    fun articlesApi(): ArticlesApi
  }
}
