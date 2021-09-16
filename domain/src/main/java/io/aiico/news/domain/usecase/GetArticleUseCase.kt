package io.aiico.news.domain.usecase

import io.aiico.news.domain.model.Article
import io.aiico.news.domain.repository.ArticlesRepository
import javax.inject.Inject

class GetArticleUseCase @Inject constructor(
  private val articlesRepository: ArticlesRepository
) {

  suspend operator fun invoke(id: String): Article = articlesRepository.getArticle(id)
}
