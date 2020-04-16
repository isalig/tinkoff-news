package io.aiico.tnews.presentation.detailed

import io.aiico.tnews.presentation.BaseMvpPresenter
import io.aiico.tnews.domain.NewsInteractor
import io.aiico.tnews.presentation.addTo
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
            .getDetailedNews(newsId, forceRefresh)
            .doOnSubscribe { viewState.showLoading(true) }
            .doAfterTerminate { viewState.showLoading(false) }
            .subscribe(
                { details -> viewState.showNewsDetails(details) },
                { viewState.showError() }
            )
            .addTo(compositeDisposable)
    }
}