package io.aiico.tnews.domain

import io.aiico.tnews.data.api.NewsApi
import io.aiico.tnews.data.database.NewsDao
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsApi: NewsApi, private val newsDao: NewsDao) {

    fun syncNewsList(): Single<List<News>> =
        newsApi
            .getNewsTitles()
            .map { response -> response.payload }
            .toObservable()
            .flatMapIterable { titles -> titles }
            .map { title ->
                News(
                    id = title.id,
                    title = title.text,
                    publicationDate = title.publicationDate.milliseconds
                )
            }
            .toList()
            .doOnSuccess { news ->
                newsDao.rewriteNews(news)
            }

    fun getNewsList(): Single<List<News>> =
        newsDao
            .getAllNews()
            .subscribeOn(Schedulers.io())
            .flatMap { localNews ->
                if (localNews.isEmpty()) {
                    syncNewsList()
                } else {
                    Single.just(localNews)
                }
            }

    fun syncNews(id: String): Single<News> =
        newsApi
            .getDetailedNews(id)
            .map { response -> response.payload }
            .map { detailedNews ->
                News(
                    id = detailedNews.title.id,
                    title = detailedNews.title.text,
                    publicationDate = detailedNews.title.publicationDate.milliseconds,
                    content = detailedNews.content
                )
            }
            .doOnSuccess { news ->
                newsDao.update(news)
            }

    fun getNews(id: String): Single<News> =
        newsDao
            .getNews(id)
            .flatMap { localNews ->
                if (localNews.isEmpty() || localNews.first().content.isEmpty()) {
                    syncNews(id)
                } else {
                    Single.just(localNews.first())
                }
            }
}