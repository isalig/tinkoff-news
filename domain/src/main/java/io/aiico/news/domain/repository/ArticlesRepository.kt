package io.aiico.news.domain.repository

import io.aiico.news.domain.model.Article
import io.reactivex.Single

interface ArticlesRepository {

  fun getArticles(): Single<List<Article>>

  fun getArticle(id: String): Single<Article>
}
