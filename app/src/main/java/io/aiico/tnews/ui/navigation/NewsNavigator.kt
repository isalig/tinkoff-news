package io.aiico.tnews.ui.navigation

import io.aiico.news.feature.feed.navigation.FeedRouter

class NewsNavigator : FeedRouter {

  private var router: NewsRouter? = null

  fun setRouter(router: NewsRouter) {
    this.router = router
  }

  fun removeRouter() {
    router = null
  }

  fun showFeed() {
    router?.showFeed()
  }

  override fun showArticle(id: String) {
    router?.showArticle(id)
  }
}
