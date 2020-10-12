package io.aiico.tnews.presentation.list

import io.aiico.news.domain.model.Article

sealed class NewsTitlesViewState(
    val titles: List<Article> = emptyList(),
    val errorMessage: String = "",
    val showLoading: Boolean = false,
    val showEmptyLoading: Boolean = false,
    val showList: Boolean = false,
    val showListPlaceholder: Boolean = false,
    val showError: Boolean = false,
    val showEmptyError: Boolean = false
)

class EmptyLoading : NewsTitlesViewState(showEmptyLoading = true)

class Loading(titles: List<Article>) : NewsTitlesViewState(titles = titles, showList = true, showLoading = true)

class Loaded(titles: List<Article>) : NewsTitlesViewState(titles = titles, showList = true)

class EmptyLoaded : NewsTitlesViewState(showListPlaceholder = true)

class Error(titles: List<Article>, message: String) : NewsTitlesViewState(titles = titles, errorMessage = message)

class EmptyError(message: String) : NewsTitlesViewState(errorMessage = message, showEmptyError = true)
