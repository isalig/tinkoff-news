package io.aiico.tnews.presentation.navigation

import androidx.fragment.app.FragmentManager
import io.aiico.tnews.presentation.detailed.ArticleFragment
import io.aiico.tnews.presentation.list.FeedFragment

class NewsRouter(private val fragmentManager: FragmentManager, private val containerId: Int) {

  fun showNewsList() {
    fragmentManager
      .beginTransaction()
      .replace(containerId, FeedFragment.newInstance())
      .commit()
  }

  fun showDetailedNews(id: String) {
    fragmentManager
      .beginTransaction()
      .replace(containerId, ArticleFragment.newInstance(id))
      .addToBackStack(null)
      .commit()
  }
}
