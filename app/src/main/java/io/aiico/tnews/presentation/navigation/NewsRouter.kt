package io.aiico.tnews.presentation.navigation

import androidx.fragment.app.FragmentManager
import io.aiico.tnews.presentation.detailed.DetailedNewsFragment
import io.aiico.tnews.presentation.list.NewsTitlesFragment

class NewsRouter(private val fragmentManager: FragmentManager, private val containerId: Int) {

  fun showNewsList() {
    fragmentManager
      .beginTransaction()
      .replace(containerId, NewsTitlesFragment.newInstance())
      .commit()
  }

  fun showDetailedNews(id: String) {
    fragmentManager
      .beginTransaction()
      .replace(containerId, DetailedNewsFragment.newInstance(id))
      .addToBackStack(null)
      .commit()
  }
}
