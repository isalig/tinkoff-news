package io.aiico.tnews.list

import io.aiico.tnews.NewsTitle
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface NewsTitlesView : MvpView {

    fun showNewsTitles(titles: List<NewsTitle>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showError()

    fun showLoading(isLoading: Boolean)
}