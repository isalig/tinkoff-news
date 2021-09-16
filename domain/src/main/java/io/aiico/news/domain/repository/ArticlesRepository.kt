package io.aiico.news.domain.repository

import io.aiico.news.domain.model.Article

interface ArticlesRepository {

  suspend fun getArticles(): List<Article>

  suspend fun getArticle(id: String): Article
}
