package io.aiico.news.feature.article.ui

import io.aiico.news.shared.editorial.domain.model.Article

sealed class ViewState

object Error: ViewState()

object Loading: ViewState()

data class Reloading(val article: Article): ViewState()

data class Success(val article: Article): ViewState()
