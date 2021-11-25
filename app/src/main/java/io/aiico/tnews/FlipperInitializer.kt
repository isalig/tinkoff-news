package io.aiico.tnews

import android.app.Application
import okhttp3.Interceptor

interface FlipperInitializer {

  fun initialize(app: Application): Interceptor?
}
