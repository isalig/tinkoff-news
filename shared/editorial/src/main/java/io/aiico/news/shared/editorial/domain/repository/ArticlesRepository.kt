package io.aiico.news.shared.editorial.domain.repository

import io.aiico.news.shared.editorial.domain.model.Article

interface ArticlesRepository {

  suspend fun getArticles(): List<Article>

  suspend fun getArticle(id: String): Article
}
