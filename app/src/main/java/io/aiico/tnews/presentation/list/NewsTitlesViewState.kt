package io.aiico.tnews.presentation.list

import io.aiico.tnews.domain.News

sealed class NewsTitlesViewState(
    val isLoading: Boolean = false,
    val titles: List<News> = emptyList(),
    val errorMessage: String = ""
)

class EmptyLoading : NewsTitlesViewState(isLoading = true)

class Loading(titles: List<News>) : NewsTitlesViewState(titles = titles)

class Loaded(titles: List<News>) : NewsTitlesViewState(titles = titles)

class EmptyLoaded : NewsTitlesViewState()

class Error(message: String) : NewsTitlesViewState(errorMessage = message)

class EmptyError(message: String) : NewsTitlesViewState(errorMessage = message)
