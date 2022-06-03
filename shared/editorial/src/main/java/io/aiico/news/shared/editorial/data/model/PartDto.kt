package io.aiico.news.shared.editorial.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PartDto(
  @SerialName("id") val id: String?,
  @SerialName("title") val title: String?
)
