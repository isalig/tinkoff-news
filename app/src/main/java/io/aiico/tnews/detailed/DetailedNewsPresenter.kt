package io.aiico.tnews.detailed

import io.aiico.tnews.BaseMvpPresenter
import io.aiico.tnews.NewsInteractor
import io.aiico.tnews.addTo
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class DetailedNewsPresenter @Inject constructor(
    private val newsId: String,
    private val newsInteractor: NewsInteractor
) : BaseMvpPresenter<DetailedNewsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        onRefresh()
    }

    fun onRefresh() {
        newsInteractor
            .getDetailedNews(newsId)
            .doOnSubscribe { viewState.showLoading(true) }
            .doAfterTerminate { viewState.showLoading(false) }
            .subscribe(
                { details -> viewState.showNewsDetails(details) },
                { viewState.showError() }
            )
            .addTo(compositeDisposable)
    }
}