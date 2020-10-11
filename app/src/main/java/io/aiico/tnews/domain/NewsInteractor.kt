package io.aiico.tnews.domain

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsInteractor @Inject constructor(private val newsRepository: NewsRepository) {

    fun getArticles(): Single<List<News>> =
            newsRepository.loadNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getArticle(id: String): Single<News> =
            newsRepository.loadArticle(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
