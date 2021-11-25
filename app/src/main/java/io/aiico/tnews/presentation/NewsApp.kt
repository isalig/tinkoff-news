package io.aiico.tnews.presentation

import android.app.Application
import io.aiico.tnews.FlipperInitializerImpl
import okhttp3.Interceptor

class NewsApp : Application() {

  lateinit var appComponent: AppComponent

  private var flipperInterceptor: Interceptor? = null

  override fun onCreate() {
    super.onCreate()
    initFlipper()
    initAppComponent()
  }

  private fun initAppComponent() {
    appComponent = AppComponent.create(this, flipperInterceptor)
  }

  private fun initFlipper() {
    flipperInterceptor = FlipperInitializerImpl.initialize(this)
  }
}
