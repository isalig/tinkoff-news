package io.aiico.news.domain.usecase

import io.aiico.news.domain.model.Article
import io.aiico.news.domain.repository.ArticlesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetArticleUseCase @Inject constructor(
  private val articlesRepository: ArticlesRepository
) {

  operator fun invoke(id: String): Single<Article> = articlesRepository.getArticle(id)
}
