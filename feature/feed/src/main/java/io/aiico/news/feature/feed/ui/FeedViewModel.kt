package io.aiico.news.feature.feed.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.aiico.news.domain.usecase.GetArticlesListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FeedViewModel @AssistedInject constructor(
  private val getArticles: GetArticlesListUseCase,
//  private val navigator: NewsNavigator,
  private val stateMachine: FeedStateMachine
) : ViewModel() {

  private val _state = MutableStateFlow(stateMachine.state)
  val state = _state.asStateFlow()

  init {
    loadNewsTitles()
  }

  fun onTitleClick(titleId: String) {
//    navigator.showArticle(titleId)
  }

  fun onRefresh() {
    loadNewsTitles()
  }

  private fun loadNewsTitles() = viewModelScope.launch {
    updateState { stateMachine.onLoading() }
    try {
      updateState { stateMachine -> stateMachine.onLoaded(getArticles()) }
    } catch (error: Throwable) {
      updateState { stateMachine ->
        stateMachine.onError(error.message ?: "Unknown error")
      }
    }
  }

  private suspend inline fun updateState(stateAction: (FeedStateMachine) -> Unit) {
    stateAction.invoke(stateMachine)
    _state.emit(stateMachine.state)
  }

  @AssistedFactory
  interface Factory {
    fun create(): FeedViewModel
  }
}
