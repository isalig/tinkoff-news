package io.aiico.news.shared.editorial.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
  @SerialName("response") val response: NewResponseContentDto
)
