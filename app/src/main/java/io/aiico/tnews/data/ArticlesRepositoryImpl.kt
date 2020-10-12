package io.aiico.tnews.data

import io.aiico.tnews.data.api.NewsApi
import io.aiico.tnews.data.converter.ArticleMapper
import io.aiico.news.domain.ArticlesRepository
import io.aiico.news.domain.model.Article
import io.reactivex.Single
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : ArticlesRepository {

    override fun loadArticles(): Single<List<Article>> =
        newsApi
            .getNewsTitles()
            .map { response -> response.response.news }
            .toObservable()
            .flatMapIterable { articles -> articles }
            .map(ArticleMapper::Article)
            .toList()

    override fun loadArticle(id: String): Single<Article> =
        loadArticles()
            .map { localNews ->
                localNews.find { it.id == id }
            }
}
