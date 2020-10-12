package io.aiico.news.domain

import io.aiico.news.domain.model.Article
import io.reactivex.Single

interface ArticlesRepository {

    fun loadArticles(): Single<List<Article>>

    fun loadArticle(id: String): Single<Article>
}
