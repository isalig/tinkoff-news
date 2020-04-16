package io.aiico.tnews.presentation.navigation

class NewsNavigator {

    private var router: NewsRouter? = null

    fun setRouter(router: NewsRouter) {
        this.router = router
    }

    fun removeRouter() {
        router = null
    }

    fun showDetailedNews(newsId: String) {
        router?.showDetailedNews(newsId)
    }
}
