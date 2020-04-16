package io.aiico.tnews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import io.aiico.tnews.detailed.DetailedNewsFragment
import io.aiico.tnews.list.NewsTitlesFragment

class MainActivity : AppCompatActivity(), CommonDependenciesReceiver, NewsNavigator {

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
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentLifecycleCallbacks)
    }

    override fun navigateToDetails(id: String) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            .replace(android.R.id.content, DetailedNewsFragment.newInstance(id))
            .addToBackStack(null)
            .commit()
    }
}
