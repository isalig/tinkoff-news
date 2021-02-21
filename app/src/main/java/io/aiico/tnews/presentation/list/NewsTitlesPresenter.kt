package io.aiico.tnews.presentation.list

import io.aiico.news.domain.usecase.GetArticlesListUseCase
import io.aiico.tnews.presentation.BasePresenter
import io.aiico.tnews.presentation.addTo
import io.aiico.tnews.presentation.navigation.NewsNavigator
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class NewsTitlesPresenter @Inject constructor(
  private val titlesStateMachine: TitlesStateMachine,
  private val getArticles: GetArticlesListUseCase,
  private val navigator: NewsNavigator,
  private val stateMachine: TitlesStateMachine
) : BasePresenter<NewsTitlesView>() {

  init {
    loadNewsTitles(false)
  }

  override fun attachView(view: NewsTitlesView) {
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

  private inline fun updateState(stateAction: (TitlesStateMachine) -> Unit) {
    stateAction.invoke(stateMachine)
    view?.applyState(stateMachine.state)
  }
}
