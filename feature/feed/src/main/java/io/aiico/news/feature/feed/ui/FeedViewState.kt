package io.aiico.news.feature.feed.ui

import io.aiico.news.domain.model.Article

sealed class FeedViewState(
    val titles: List<Article> = emptyList(),
    val errorMessage: String = "",
    val showLoading: Boolean = false,
    val showEmptyLoading: Boolean = false,
    val showList: Boolean = false,
    val showListPlaceholder: Boolean = false,
    val showError: Boolean = false,
    val showEmptyError: Boolean = false
)

class EmptyLoading : FeedViewState(showEmptyLoading = true)

class Loading(titles: List<Article>) : FeedViewState(titles = titles, showList = true, showLoading = true)

class Loaded(titles: List<Article>) : FeedViewState(titles = titles, showList = true)

class EmptyLoaded : FeedViewState(showListPlaceholder = true)

class Error(titles: List<Article>, message: String) : FeedViewState(titles = titles, errorMessage = message)

class EmptyError(message: String) : FeedViewState(errorMessage = message, showEmptyError = true)
