package io.aiico.news.data.converter

import io.aiico.news.domain.model.Part
import io.aiico.news.data.model.PartDto

@Suppress("FunctionName")
object PartMapper {

  fun Part(partDto: PartDto): Part = with(partDto) {
    Part(
        id = id.orEmpty(),
        title = title.orEmpty()
    )
  }
}
