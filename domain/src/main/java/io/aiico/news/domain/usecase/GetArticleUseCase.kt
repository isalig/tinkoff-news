package io.aiico.news.domain.usecase

import io.aiico.news.domain.model.Article
import io.aiico.news.domain.repository.ArticlesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetArticleUseCase @Inject constructor(
  private val articlesRepository: ArticlesRepository
) {

  suspend operator fun invoke(id: String): Article = withContext(Dispatchers.IO) {
    articlesRepository.getArticle(id)
  }
}
