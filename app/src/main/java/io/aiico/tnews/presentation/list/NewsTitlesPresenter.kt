package io.aiico.tnews.presentation.list

import android.util.Log
import io.aiico.tnews.presentation.BaseMvpPresenter
import io.aiico.tnews.domain.NewsInteractor
import io.aiico.tnews.presentation.navigation.NewsNavigator
import io.aiico.tnews.presentation.addTo
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class NewsTitlesPresenter @Inject constructor(
    private val newsInteractor: NewsInteractor,
    private val navigator: NewsNavigator
) : BaseMvpPresenter<NewsTitlesView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadNewsTitles(false)
    }

    fun onTitleClick(titleId: String) {
        navigator.showDetailedNews(titleId)
    }

    fun onRefresh() {
        loadNewsTitles(true)
    }

    private fun loadNewsTitles(forceRefresh: Boolean) {
        newsInteractor
            .getNewsList(forceRefresh)
            .doOnSubscribe { viewState.showLoading(true) }
            .doAfterTerminate { viewState.showLoading(false) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { news -> viewState.showNewsTitles(news) },
                { error ->
                    viewState.showError()
                    Log.e("NewsTitlesPresenter", "Error", error)
                }
            ).addTo(compositeDisposable)
    }
}
