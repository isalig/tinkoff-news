package io.aiico.news.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsResponse(
  @SerialName("response") val response: NewResponseContentDto
)
