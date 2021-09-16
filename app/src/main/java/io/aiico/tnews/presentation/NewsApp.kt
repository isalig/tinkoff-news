package io.aiico.tnews.presentation

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import io.aiico.tnews.BuildConfig
import okhttp3.Interceptor

class NewsApp : Application() {

  lateinit var appComponent: AppComponent

  override fun onCreate() {
    super.onCreate()
    val networkInterceptor = initFlipper()
    initAppComponent(networkInterceptor)
  }

  private fun initAppComponent(networkInterceptor: Interceptor) {
    appComponent = AppComponent.create(this, networkInterceptor)
  }

  private fun initFlipper(): Interceptor {
    SoLoader.init(this, false)

    // TODO do not provide this plugin for release builds
    val networkPlugin = NetworkFlipperPlugin()
    val networkInterceptor = FlipperOkhttpInterceptor(networkPlugin)

    if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
      AndroidFlipperClient.getInstance(this).apply {
        addPlugin(InspectorFlipperPlugin(this@NewsApp, DescriptorMapping.withDefaults()))
        addPlugin(networkPlugin)
        start()
      }
    }

    return networkInterceptor
  }
}
