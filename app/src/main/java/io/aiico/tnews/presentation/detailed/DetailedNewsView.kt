package io.aiico.tnews.presentation.detailed

import io.aiico.news.domain.model.Article

interface DetailedNewsView {

  fun showNewsDetails(article: Article)

  fun showError()

  fun showLoading(isLoading: Boolean)
}
