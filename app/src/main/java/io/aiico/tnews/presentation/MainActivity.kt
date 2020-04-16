package io.aiico.tnews.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import io.aiico.tnews.presentation.di.CommonDependencies
import io.aiico.tnews.presentation.di.CommonDependenciesReceiver
import io.aiico.tnews.presentation.di.NewsFeatureClient
import io.aiico.tnews.presentation.di.component.NewsFeatureComponent
import io.aiico.tnews.presentation.list.NewsTitlesFragment
import io.aiico.tnews.presentation.navigation.NewsNavigator
import io.aiico.tnews.presentation.navigation.NewsRouter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), CommonDependenciesReceiver {

    @Inject
    lateinit var navigator: NewsNavigator

    private lateinit var component: NewsFeatureComponent
    private val addedFragment: Fragment?
        get() = supportFragmentManager.findFragmentById(android.R.id.content)

    private val fragmentLifecycleCallbacks = object : FragmentManager.FragmentLifecycleCallbacks() {

        override fun onFragmentPreCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
            if (f is NewsFeatureClient) {
                f.dispatchInjection(component)
            }
        }
    }

    override fun onProvideCommonDependencies(commonDependencies: CommonDependencies) {
        component = NewsFeatureComponent.create(commonDependencies)
        component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator.setRouter(
            NewsRouter(
                supportFragmentManager,
                android.R.id.content
            )
        )
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentLifecycleCallbacks, false)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        savedInstanceState
            ?: addedFragment
            ?: addNewsTitlesFragment()
    }

    private fun addNewsTitlesFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(android.R.id.content, NewsTitlesFragment.newInstance())
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        navigator.removeRouter()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentLifecycleCallbacks)
    }
}
