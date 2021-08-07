package io.aiico.news.data.mapper

import io.aiico.news.data.model.ArticleDto
import io.aiico.news.domain.model.Article

@Suppress("FunctionName")
object ArticleMapper {

  fun Article(articleDto: ArticleDto): Article = with(articleDto) {
    Article(
      id = id.orEmpty(),
      title = title.orEmpty(),
      image = image,
      createdTime = createdTime,
      hidden = hidden ?: false,
      slug = slug,
      parts = parts?.map(PartMapper::Part).orEmpty(),
      tags = tags?.map(TagMapper::Tag).orEmpty(),
      text = text.orEmpty(),
      shortText = shortText.orEmpty(),
    )
  }
}
