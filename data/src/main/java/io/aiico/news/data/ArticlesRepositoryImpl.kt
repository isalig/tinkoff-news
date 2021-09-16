package io.aiico.news.data

import io.aiico.news.data.api.ArticlesApi
import io.aiico.news.data.mapper.ArticleMapper
import io.aiico.news.domain.model.Article
import io.aiico.news.domain.repository.ArticlesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
  private val articlesApi: ArticlesApi
) : ArticlesRepository {

  override suspend fun getArticles(): List<Article> = withContext(Dispatchers.IO) {
    articlesApi.getNewsTitles()
      .response
      .news
      .map(ArticleMapper::Article)
  }

  override suspend fun getArticle(id: String): Article = withContext(Dispatchers.IO) {
    getArticles().first { article -> id == article.id }
  }
}
