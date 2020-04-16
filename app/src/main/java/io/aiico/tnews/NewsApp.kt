package io.aiico.tnews

import android.app.Activity
import android.app.Application
import android.os.Bundle

class NewsApp : Application() {

    private lateinit var appComponent: AppComponent

    private val activityLifecycleCallbacks = object : SimpleActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            if (activity is CommonDependenciesReceiver) {
                activity.onProvideCommonDependencies(appComponent)
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }
}