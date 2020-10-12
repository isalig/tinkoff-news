package io.aiico.tnews.domain

import io.aiico.tnews.domain.model.Article
import io.reactivex.Single

interface ArticlesRepository {

    fun loadArticles(): Single<List<Article>>

    fun loadArticle(id: String): Single<Article>
}
