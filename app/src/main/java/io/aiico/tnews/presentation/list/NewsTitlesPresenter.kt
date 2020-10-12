package io.aiico.tnews.presentation.list

import io.aiico.news.domain.NewsInteractor
import io.aiico.tnews.presentation.BaseMvpPresenter
import io.aiico.tnews.presentation.addTo
import io.aiico.tnews.presentation.navigation.NewsNavigator
import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class NewsTitlesPresenter @Inject constructor(
    private val titlesStateMachine: TitlesStateMachine,
    private val newsInteractor: NewsInteractor,
    private val navigator: NewsNavigator,
    private val stateMachine: TitlesStateMachine
) : BaseMvpPresenter<NewsTitlesView>() {

    init {
        loadNewsTitles(false)
    }

    override fun onFirstViewAttach() {
        viewState.applyState(stateMachine.state)
    }

    fun onTitleClick(titleId: String) {
        navigator.showDetailedNews(titleId)
    }

    fun onRefresh() {
        loadNewsTitles(true)
    }

    private fun loadNewsTitles(forceRefresh: Boolean) {
        newsInteractor
            .getArticles()
            .doOnSubscribe {
                updateState { stateMachine.onLoading() }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { news ->
                    updateState { stateMachine -> stateMachine.onLoaded(news) }
                },
                { error ->
                    updateState { stateMachine -> stateMachine.onError(error.message ?: "Unknown error") }
                }
            ).addTo(compositeDisposable)
    }

    private inline fun updateState(stateAction: (TitlesStateMachine) -> Unit) {
        stateAction.invoke(stateMachine)
        viewState.applyState(stateMachine.state)
    }
}
