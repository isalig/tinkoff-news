package io.aiico.tnews.data.converter

import io.aiico.news.domain.model.Part
import io.aiico.tnews.data.model.PartDto

@Suppress("FunctionName")
object PartMapper {

  fun Part(partDto: PartDto): Part = with(partDto) {
    Part(
        id = id.orEmpty(),
        title = title.orEmpty()
    )
  }
}
