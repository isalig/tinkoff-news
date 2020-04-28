package io.aiico.tnews.presentation.list

import io.aiico.tnews.domain.News

sealed class NewsTitlesViewState(
    val titles: List<News> = emptyList(),
    val errorMessage: String = "",
    val showLoading: Boolean = false,
    val showEmptyLoading: Boolean = false,
    val showList: Boolean = false,
    val showListPlaceholder: Boolean = false,
    val showError: Boolean = false,
    val showEmptyError: Boolean = false
)

class EmptyLoading : NewsTitlesViewState(showEmptyLoading = true)

class Loading(titles: List<News>) : NewsTitlesViewState(titles = titles, showList = true, showLoading = true)

class Loaded(titles: List<News>) : NewsTitlesViewState(titles = titles, showList = true)

class EmptyLoaded : NewsTitlesViewState(showListPlaceholder = true)

class Error(titles: List<News>, message: String) : NewsTitlesViewState(titles = titles, errorMessage = message)

class EmptyError(message: String) : NewsTitlesViewState(errorMessage = message, showEmptyError = true)
