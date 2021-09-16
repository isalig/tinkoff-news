package io.aiico.tnews.presentation.article

import io.aiico.news.domain.usecase.GetArticleUseCase
import io.aiico.tnews.presentation.BasePresenter
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArticlePresenter @Inject constructor(
  private val newsId: String,
  private val getArticle: GetArticleUseCase
) : BasePresenter<ArticleView>() {

  override fun attachView(view: ArticleView) {
    super.attachView(view)
    loadDetails()
  }

  fun onRefresh() {
    loadDetails()
  }

  private fun loadDetails() = presenterScope.launch {
    view?.showLoading(true)
    try {
      view?.showArticle(getArticle(newsId))
    } catch (error: Throwable) {
      view?.showError()
    } finally {
      view?.showLoading(false)
    }
  }
}
