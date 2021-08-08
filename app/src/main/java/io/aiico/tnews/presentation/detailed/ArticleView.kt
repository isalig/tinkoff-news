package io.aiico.tnews.presentation.detailed

import io.aiico.news.domain.model.Article

interface ArticleView {

  fun showArticle(article: Article)

  fun showError()

  fun showLoading(isLoading: Boolean)
}
