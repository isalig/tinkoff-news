package io.aiico.news.shared.editorial.domain.usecase

import io.aiico.news.shared.editorial.domain.model.Article
import io.aiico.news.shared.editorial.domain.repository.ArticlesRepository
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
