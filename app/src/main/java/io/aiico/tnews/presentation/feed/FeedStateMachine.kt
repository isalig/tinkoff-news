package io.aiico.tnews.presentation.feed

import io.aiico.news.domain.model.Article
import javax.inject.Inject

class FeedStateMachine @Inject constructor() {

  var state: FeedViewState = EmptyLoading()
    private set

  fun onLoading(): FeedViewState {
    state = when (state) {
        is EmptyLoading, is Loading -> state

      else -> {
        if (state.titles.isNotEmpty()) {
          Loading(state.titles)
        } else {
          EmptyLoading()
        }
      }
    }
    return state
  }

  fun onLoaded(titles: List<Article>): FeedViewState {
    state = if (titles.isNotEmpty()) {
      Loaded(titles)
    } else {
      EmptyLoaded()
    }
    return state
  }

  fun onError(errorMessage: String): FeedViewState {
    state = if (state.titles.isNotEmpty()) {
      Error(state.titles, errorMessage)
    } else {
      EmptyError(errorMessage)
    }
    return state
  }
}
