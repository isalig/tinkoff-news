package io.aiico.news.domain

import io.aiico.news.domain.model.Article
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsInteractor @Inject constructor(private val newsRepository: ArticlesRepository) {

    fun getArticles(): Single<List<Article>> =
            newsRepository.loadArticles()
            .subscribeOn(Schedulers.io())

    fun getArticle(id: String): Single<Article> =
            newsRepository.loadArticle(id)
            .subscribeOn(Schedulers.io())
}
