package io.aiico.tnews.presentation

import androidx.annotation.CallSuper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

abstract class BasePresenter<V> {

  protected val presenterScope = CoroutineScope(Job() + Dispatchers.Main)
  protected var view: V? = null

  @CallSuper
  open fun attachView(view: V) {
    this.view = view
  }

  @CallSuper
  open fun detachView() {
    presenterScope.cancel()
  }
}
