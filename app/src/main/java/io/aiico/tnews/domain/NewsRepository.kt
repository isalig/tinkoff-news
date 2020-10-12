package io.aiico.tnews.domain

import io.aiico.tnews.data.api.NewsApi
import io.aiico.tnews.data.converter.ArticleMapper
import io.aiico.tnews.data.database.NewsDao
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class NewsRepository @Inject constructor(
        private val newsApi: NewsApi,
        private val newsDao: NewsDao
) {

    fun loadNews(): Single<List<News>> =
            newsApi
                    .getNewsTitles()
                    .map { response -> response.response.news }
                    .map { it.map(ArticleMapper::Article) }
                    .toObservable()
                    .flatMapIterable { titles -> titles }
                    .map { article ->
                        News(
                                id = article.id,
                                title = article.title,
                                publicationDate = Date().time,
                                content = article.text
                        )
                    }
                    .toList()
                    .doOnSuccess { news ->
                        newsDao.rewriteNews(news)
                    }

    fun loadArticle(id: String): Single<News> =
            newsDao
                    .getNews(id)
                    .map { localNews ->
                        localNews.find { it.id == id }
                    }
}
