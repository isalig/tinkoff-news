package io.aiico.news.domain.usecase

import io.aiico.news.domain.model.Article
import io.aiico.news.domain.repository.ArticlesRepository
import javax.inject.Inject

class GetArticlesListUseCase @Inject constructor(
  private val articlesRepository: ArticlesRepository
) {

  suspend operator fun invoke(): List<Article> = articlesRepository.getArticles()
}
