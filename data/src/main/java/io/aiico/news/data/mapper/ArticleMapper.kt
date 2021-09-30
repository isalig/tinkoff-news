package io.aiico.news.data.mapper

import io.aiico.news.data.model.ArticleDto
import io.aiico.news.domain.model.Article
import org.threeten.bp.ZonedDateTime

@Suppress("FunctionName")
object ArticleMapper {

  fun Article(articleDto: ArticleDto): Article = with(articleDto) {
    Article(
      id = id.orEmpty(),
      title = title.orEmpty(),
      image = image,
      publicationDateTime = publicationDateTime?.let(ZonedDateTime::parse),
      hidden = hidden ?: false,
      slug = slug,
      parts = parts?.map(PartMapper::Part).orEmpty(),
      tags = tags?.map(TagMapper::Tag).orEmpty(),
      text = text.orEmpty(),
      shortText = shortText.orEmpty(),
    )
  }
}
