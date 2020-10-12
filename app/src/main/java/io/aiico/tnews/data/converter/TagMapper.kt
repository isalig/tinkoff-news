package io.aiico.tnews.data.converter

import io.aiico.tnews.data.model.TagDto
import io.aiico.news.domain.model.Tag

@Suppress("FunctionName")
object TagMapper {

    fun Tag(tagDto: TagDto): Tag = with(tagDto) {
        Tag(
                id = id.orEmpty(),
                value = value.orEmpty()
        )
    }
}
