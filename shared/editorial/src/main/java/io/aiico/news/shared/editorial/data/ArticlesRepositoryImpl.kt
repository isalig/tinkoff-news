package io.aiico.news.shared.editorial.data

import io.aiico.news.shared.editorial.data.api.ArticlesApi
import io.aiico.news.shared.editorial.data.mapper.ArticleMapper
import io.aiico.news.shared.editorial.domain.model.Article
import io.aiico.news.shared.editorial.domain.repository.ArticlesRepository
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
  private val articlesApi: ArticlesApi
) : ArticlesRepository {

  override suspend fun getArticles(): List<Article> =
    articlesApi.getNewsTitles()
      .response
      .news
      .map(ArticleMapper::Article)

  override suspend fun getArticle(id: String): Article =
    getArticles().first { article -> id == article.id }
}
