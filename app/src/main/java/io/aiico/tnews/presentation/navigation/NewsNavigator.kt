package io.aiico.tnews.presentation.navigation

class NewsNavigator {

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

  fun showArticle(newsId: String) {
    router?.showArticle(newsId)
  }
}
