package io.aiico.news.shared.editorial.data.mapper

import io.aiico.news.shared.editorial.data.model.TagDto
import io.aiico.news.shared.editorial.domain.model.Tag

@Suppress("FunctionName")
object TagMapper {

  fun Tag(tagDto: TagDto): Tag = with(tagDto) {
    Tag(id = id.orEmpty(), value = value.orEmpty())
  }
}
