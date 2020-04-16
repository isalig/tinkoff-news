package io.aiico.tnews.presentation

import android.app.Activity
import android.app.Application
import android.os.Bundle
import io.aiico.tnews.presentation.di.CommonDependenciesReceiver
import io.aiico.tnews.presentation.di.component.AppComponent

class NewsApp : Application() {

    private lateinit var appComponent: AppComponent

    private val activityLifecycleCallbacks = object :
        SimpleActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            if (activity is CommonDependenciesReceiver) {
                activity.onProvideCommonDependencies(appComponent)
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = AppComponent.create(this)
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks)
    }
}