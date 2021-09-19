package io.aiico.tnews.presentation.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.aiico.news.domain.usecase.GetArticleUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArticleViewModel @AssistedInject constructor(
  @Assisted private val articleId: String,
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
      _state.emit(Success(getArticle(articleId)))
    } catch (error: Throwable) {
      _state.emit(Error)
    }
  }

  @AssistedFactory
  interface Factory {
    fun create(articleId: String): ArticleViewModel
  }
}
