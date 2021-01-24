package io.aiico.tnews.presentation

import android.app.Application
import android.util.Log
import io.aiico.tnews.presentation.di.component.AppComponent
import io.reactivex.plugins.RxJavaPlugins

class NewsApp : Application() {

  lateinit var appComponent: AppComponent

  override fun onCreate() {
    super.onCreate()
    appComponent = AppComponent.create(this)
    RxJavaPlugins.setErrorHandler { throwable ->
      Log.e("Tinkoff", throwable.message, throwable)
      showToast(throwable.message ?: "Unknown error, see log message")
    }
  }
}
