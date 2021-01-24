package io.aiico.tnews.presentation.detailed

import io.aiico.news.domain.NewsInteractor
import io.aiico.tnews.presentation.BasePresenter
import io.aiico.tnews.presentation.addTo
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class DetailedNewsPresenter @Inject constructor(
  private val newsId: String,
  private val newsInteractor: NewsInteractor
) : BasePresenter<DetailedNewsView>() {

  override fun attachView(view: DetailedNewsView) {
    super.attachView(view)
    loadDetails(false)
  }

  fun onRefresh() {
    loadDetails(true)
  }

  private fun loadDetails(forceRefresh: Boolean) {
    newsInteractor
      .getArticle(newsId)
      .observeOn(AndroidSchedulers.mainThread())
      .doOnSubscribe { view?.showLoading(true) }
      .doAfterTerminate { view?.showLoading(false) }
      .subscribe(
        { details -> view?.showNewsDetails(details) },
        { view?.showError() }
      )
      .addTo(compositeDisposable)
  }
}
