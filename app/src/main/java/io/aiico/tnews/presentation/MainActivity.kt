package io.aiico.tnews.presentation

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import io.aiico.tnews.presentation.navigation.NewsNavigator
import io.aiico.tnews.presentation.navigation.NewsRouter

class MainActivity : AppCompatActivity() {

  private lateinit var navigator: NewsNavigator

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    navigator = (application as NewsApp).appComponent.newsNavigator
    navigator.setRouter(NewsRouter(supportFragmentManager, Window.ID_ANDROID_CONTENT))
    savedInstanceState ?: navigator.showNewsList()
  }

  override fun onDestroy() {
    super.onDestroy()
    navigator.removeRouter()
  }
}
