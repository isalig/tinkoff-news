package io.aiico.tnews.presentation.list

import io.aiico.tnews.domain.model.Article
import javax.inject.Inject

class TitlesStateMachine @Inject constructor() {

    var state: NewsTitlesViewState = EmptyLoading()
        private set

    fun onLoading(): NewsTitlesViewState {
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

    fun onLoaded(titles: List<Article>): NewsTitlesViewState {
        state = if (titles.isNotEmpty()) {
            Loaded(titles)
        } else {
            EmptyLoaded()
        }
        return state
    }

    fun onError(errorMessage: String): NewsTitlesViewState {
        state = if (state.titles.isNotEmpty()) {
            Error(state.titles, errorMessage)
        } else {
            EmptyError(errorMessage)
        }
        return state
    }
}
