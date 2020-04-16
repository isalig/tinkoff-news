package io.aiico.tnews.list

import io.aiico.tnews.BaseMvpPresenter
import io.aiico.tnews.NewsInteractor
import io.aiico.tnews.addTo
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class NewsTitlesPresenter @Inject constructor(
    private val newsInteractor: NewsInteractor
) : BaseMvpPresenter<NewsTitlesView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        onRefresh()
    }

    fun onRefresh() {
        newsInteractor
            .getAllNews()
            .doOnSubscribe { viewState.showLoading(true) }
            .doAfterTerminate { viewState.showLoading(false) }
            .subscribe(
                { titles -> viewState.showNewsTitles(titles) },
                { viewState.showError() }
            ).addTo(compositeDisposable)
    }
}
