package io.aiico.tnews.presentation

import androidx.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V> {

  protected val compositeDisposable = CompositeDisposable()
  protected var view: V? = null

  @CallSuper
  open fun attachView(view: V) {
    this.view = view
  }

  @CallSuper
  open fun detachView() {
    compositeDisposable.clear()
  }
}
