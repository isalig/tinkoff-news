package io.aiico.tnews.domain

import io.aiico.tnews.data.api.NewsApi
import io.aiico.tnews.data.converter.ArticleMapper
import io.aiico.tnews.domain.model.Article
import io.reactivex.Single
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsApi: NewsApi) {

    fun loadArticles(): Single<List<Article>> =
            newsApi
                    .getNewsTitles()
                    .map { response -> response.response.news }
                    .map { it.map(ArticleMapper::Article) }
                    .toObservable()
                    .flatMapIterable { titles -> titles }
                    .toList()

    fun loadArticle(id: String): Single<Article> =
            loadArticles()
                    .map { localNews ->
                        localNews.find { it.id == id }
                    }
}
