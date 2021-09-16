package io.aiico.tnews.presentation.feed

import io.aiico.news.domain.usecase.GetArticlesListUseCase
import io.aiico.tnews.presentation.BasePresenter
import io.aiico.tnews.presentation.navigation.NewsNavigator
import kotlinx.coroutines.launch
import javax.inject.Inject

class FeedPresenter @Inject constructor(
  private val getArticles: GetArticlesListUseCase,
  private val navigator: NewsNavigator,
  private val stateMachine: FeedStateMachine
) : BasePresenter<FeedView>() {

  init {
    loadNewsTitles()
  }

  override fun attachView(view: FeedView) {
    super.attachView(view)
    view.applyState(stateMachine.state)
  }

  fun onTitleClick(titleId: String) {
    navigator.showArticle(titleId)
  }

  fun onRefresh() {
    loadNewsTitles()
  }

  private fun loadNewsTitles() = presenterScope.launch {
    updateState { stateMachine.onLoading() }
    try {
      updateState { stateMachine -> stateMachine.onLoaded(getArticles()) }
    } catch (error: Throwable) {
      updateState { stateMachine ->
        stateMachine.onError(error.message ?: "Unknown error")
      }
    }
  }

  private inline fun updateState(stateAction: (FeedStateMachine) -> Unit) {
    stateAction.invoke(stateMachine)
    view?.applyState(stateMachine.state)
  }
}
