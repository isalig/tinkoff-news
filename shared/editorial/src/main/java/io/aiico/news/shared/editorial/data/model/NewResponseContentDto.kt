package io.aiico.news.shared.editorial.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewResponseContentDto(
  @SerialName("items") val news: List<ArticleDto>,
  @SerialName("total") val total: Int,
)
