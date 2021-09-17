package io.aiico.tnews.presentation.article

import io.aiico.news.domain.model.Article

sealed class ViewState

object Error: ViewState()

object Loading: ViewState()

data class Reloading(val article: Article): ViewState()

data class Success(val article: Article): ViewState()
