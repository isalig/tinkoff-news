package io.aiico.tnews.presentation.navigation

import androidx.fragment.app.FragmentManager
import io.aiico.tnews.presentation.article.ArticleFragment
import io.aiico.tnews.presentation.feed.FeedFragment

class NewsRouter(private val fragmentManager: FragmentManager, private val containerId: Int) {

  fun showFeed() {
    fragmentManager
      .beginTransaction()
      .replace(containerId, FeedFragment.newInstance())
      .commit()
  }

  fun showArticle(id: String) {
    fragmentManager
      .beginTransaction()
      .replace(containerId, ArticleFragment.newInstance(id))
      .addToBackStack(null)
      .commit()
  }
}
