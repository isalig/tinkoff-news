package io.aiico.news.data.converter

import io.aiico.news.data.model.TagDto
import io.aiico.news.domain.model.Tag

@Suppress("FunctionName")
object TagMapper {

  fun Tag(tagDto: TagDto): Tag = with(tagDto) {
    Tag(id = id.orEmpty(), value = value.orEmpty())
  }
}
