package io.aiico.tnews.presentation.list

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface NewsTitlesView : MvpView {

  fun applyState(state: NewsTitlesViewState)
}
