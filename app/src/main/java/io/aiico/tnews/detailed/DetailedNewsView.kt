package io.aiico.tnews.detailed

import io.aiico.tnews.DetailedNews
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface DetailedNewsView : MvpView {

    fun showNewsDetails(news: DetailedNews)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showError()
}
