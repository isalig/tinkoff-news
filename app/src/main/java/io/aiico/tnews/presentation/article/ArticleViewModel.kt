package io.aiico.tnews.presentation.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.aiico.news.domain.usecase.GetArticleUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArticleViewModel @Inject constructor(
  private val newsId: String,
  private val getArticle: GetArticleUseCase
) : ViewModel() {

  private val _state = MutableStateFlow<ViewState>(Loading)
  val state = _state.asStateFlow()

  init {
    loadDetails()
  }

  fun onRefresh() {
    loadDetails()
  }

  private fun loadDetails() = viewModelScope.launch {
    val currentState = _state.value
    val loadingState = if (currentState is Success) Reloading(currentState.article) else Loading
    _state.emit(loadingState)
    try {
      _state.emit(Success(getArticle(newsId)))
    } catch (error: Throwable) {
      _state.emit(Error)
    }
  }
}
