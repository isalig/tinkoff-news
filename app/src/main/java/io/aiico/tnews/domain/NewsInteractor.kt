package io.aiico.tnews.domain

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsInteractor @Inject constructor(private val newsRepository: NewsRepository) {

    fun getNewsList(forceRefresh: Boolean = false): Single<List<News>> =
        if (forceRefresh) {
            newsRepository.syncNewsList()
        } else {
            newsRepository.getNewsList()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getDetailedNews(id: String, forceRefresh: Boolean = false): Single<News> =
        if (forceRefresh) {
            newsRepository.syncNews(id)
        } else {
            newsRepository.getNews(id)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
