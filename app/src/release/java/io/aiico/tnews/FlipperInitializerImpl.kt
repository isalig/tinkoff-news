package io.aiico.tnews

import android.app.Application
import okhttp3.Interceptor

object FlipperInitializerImpl : FlipperInitializer {

  override fun initialize(app: Application): Interceptor? = null
}
