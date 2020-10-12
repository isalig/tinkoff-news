package io.aiico.tnews.data.converter

import io.aiico.news.domain.model.Tag
import io.aiico.tnews.data.model.TagDto

@Suppress("FunctionName")
object TagMapper {

  fun Tag(tagDto: TagDto): Tag = with(tagDto) {
    Tag(
        id = id.orEmpty(),
        value = value.orEmpty()
    )
  }
}
