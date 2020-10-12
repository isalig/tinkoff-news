package io.aiico.tnews.data.converter

import io.aiico.tnews.data.model.PartDto
import io.aiico.tnews.domain.model.Part

@Suppress("FunctionName")
object PartMapper {

    fun Part(partDto: PartDto): Part = with(partDto) {
        Part(
                id = id.orEmpty(),
                title = title.orEmpty()
        )
    }
}
