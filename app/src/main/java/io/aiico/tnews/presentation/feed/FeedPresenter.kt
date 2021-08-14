package io.aiico.tnews.presentation.feed

import io.aiico.news.domain.usecase.GetArticlesListUseCase
import io.aiico.tnews.presentation.BasePresenter
import io.aiico.tnews.presentation.addTo
import io.aiico.tnews.presentation.navigation.NewsNavigator
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class FeedPresenter @Inject constructor(
  private val getArticles: GetArticlesListUseCase,
  private val navigator: NewsNavigator,
  private val stateMachine: FeedStateMachine
) : BasePresenter<FeedView>() {

  init {
    loadNewsTitles(false)
  }

  override fun attachView(view: FeedView) {
    super.attachView(view)
    view.applyState(stateMachine.state)
  }

  fun onTitleClick(titleId: String) {
    navigator.showDetailedNews(titleId)
  }

  fun onRefresh() {
    loadNewsTitles(true)
  }

  private fun loadNewsTitles(forceRefresh: Boolean) {
    getArticles()
      .doOnSubscribe { updateState { stateMachine.onLoading() } }
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(
        { news -> updateState { stateMachine -> stateMachine.onLoaded(news) } },
        { error -> updateState { stateMachine -> stateMachine.onError(error.message ?: "Unknown error") } }
      )
      .addTo(compositeDisposable)
  }

  private inline fun updateState(stateAction: (FeedStateMachine) -> Unit) {
    stateAction.invoke(stateMachine)
    view?.applyState(stateMachine.state)
  }
}
