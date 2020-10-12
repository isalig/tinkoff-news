package io.aiico.tnews.presentation.detailed

import io.aiico.tnews.presentation.BaseMvpPresenter
import io.aiico.news.domain.NewsInteractor
import io.aiico.tnews.presentation.addTo
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class DetailedNewsPresenter @Inject constructor(
    private val newsId: String,
    private val newsInteractor: NewsInteractor
) : BaseMvpPresenter<DetailedNewsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadDetails(false)
    }

    fun onRefresh() {
        loadDetails(true)
    }

    private fun loadDetails(forceRefresh: Boolean) {
        newsInteractor
            .getArticle(newsId)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoading(true) }
            .doAfterTerminate { viewState.showLoading(false) }
            .subscribe(
                { details -> viewState.showNewsDetails(details) },
                { viewState.showError() }
            )
            .addTo(compositeDisposable)
    }
}
