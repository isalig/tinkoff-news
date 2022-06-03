package io.aiico.news.shared.editorial.data.mapper

import io.aiico.news.shared.editorial.data.model.PartDto
import io.aiico.news.shared.editorial.domain.model.Part

@Suppress("FunctionName")
object PartMapper {

  fun Part(partDto: PartDto): Part = with(partDto) {
    Part(id = id.orEmpty(), title = title.orEmpty())
  }
}
