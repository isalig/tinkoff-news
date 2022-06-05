package io.aiico.tnews.ui

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import io.aiico.news.shared.di.DependenciesProvider
import io.aiico.tnews.ui.navigation.NewsNavigator
import io.aiico.tnews.ui.navigation.NewsRouter

class MainActivity : AppCompatActivity(), DependenciesProvider {

  private lateinit var navigator: NewsNavigator
  private val component: AppComponent
    get() = (application as NewsApp).appComponent

  override fun getDependencies(): Any = component

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    navigator = component.newsNavigator
    navigator.setRouter(NewsRouter(supportFragmentManager, Window.ID_ANDROID_CONTENT))
    savedInstanceState ?: navigator.showFeed()
  }

  override fun onDestroy() {
    super.onDestroy()
    navigator.removeRouter()
  }
}
