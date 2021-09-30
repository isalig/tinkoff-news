package io.aiico.news.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleDto(
  @SerialName("id") val id: String?,
  @SerialName("title") val title: String?,
  @SerialName("imageMobile") val image: String?,
  @SerialName("publishedAt") val publicationDateTime: String?,
  @SerialName("hidden") val hidden: Boolean?,
  @SerialName("slug") val slug: String?,
  @SerialName("parts") val parts: List<PartDto>?,
  @SerialName("tags") val tags: List<TagDto>?,
  @SerialName("text") val text: String?,
  @SerialName("textshort") val shortText: String?
)
