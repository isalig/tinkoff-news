package io.aiico.news.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TagDto(
  @SerialName("id") val id: String?,
  @SerialName("value") val value: String?
)
