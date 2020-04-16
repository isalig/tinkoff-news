package io.aiico.tnews.presentation.detailed

import io.aiico.tnews.domain.News
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface DetailedNewsView : MvpView {

    fun showNewsDetails(news: News)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showError()

    fun showLoading(isLoading: Boolean)
}
