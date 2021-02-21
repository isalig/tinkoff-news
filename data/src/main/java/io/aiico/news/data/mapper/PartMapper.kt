package io.aiico.news.data.mapper

import io.aiico.news.data.model.PartDto
import io.aiico.news.domain.model.Part

@Suppress("FunctionName")
object PartMapper {

  fun Part(partDto: PartDto): Part = with(partDto) {
    Part(id = id.orEmpty(), title = title.orEmpty())
  }
}
