package io.aiico.news.data

import io.aiico.news.data.api.ArticlesApi
import io.aiico.news.data.mapper.ArticleMapper
import io.aiico.news.data.model.NewResponseContentDto
import io.aiico.news.data.model.NewsResponse
import io.aiico.news.domain.model.Article
import io.aiico.news.domain.repository.ArticlesRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
  private val articlesApi: ArticlesApi
) : ArticlesRepository {

  override fun getArticles(): Single<List<Article>> = articlesApi
    .getNewsTitles()
    .map(NewsResponse::response)
    .map(NewResponseContentDto::news)
    .toObservable()
    .flatMapIterable { articles -> articles }
    .map(ArticleMapper::Article)
    .toList()
    .subscribeOn(Schedulers.io())

  override fun getArticle(id: String): Single<Article> = getArticles()
    .map { localNews -> localNews.find { it.id == id } }
}
