package io.aiico.tnews.domain

import io.aiico.tnews.domain.model.Article
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsInteractor @Inject constructor(private val newsRepository: NewsRepository) {

    fun getArticles(): Single<List<Article>> =
            newsRepository.loadArticles()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getArticle(id: String): Single<Article> =
            newsRepository.loadArticle(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}
