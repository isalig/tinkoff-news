package io.aiico.tnews

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import okhttp3.Interceptor

object FlipperInitializerImpl : FlipperInitializer {

  override fun initialize(app: Application): Interceptor? =
    if (FlipperUtils.shouldEnableFlipper(app)) {
      SoLoader.init(app, false)
      val networkPlugin = NetworkFlipperPlugin()
      AndroidFlipperClient.getInstance(app).apply {
        addPlugin(InspectorFlipperPlugin(app, DescriptorMapping.withDefaults()))
        addPlugin(networkPlugin)
        start()
      }
      FlipperOkhttpInterceptor(networkPlugin)
    } else {
      null
    }
}
