package io.aiico.news.data.model

import com.google.gson.annotations.SerializedName

data class ArticleDto(
  @SerializedName("id") val id: String?,
  @SerializedName("title") val title: String?,
  @SerializedName("image") val image: String?,
  @SerializedName("lang") val lang: String?,
  @SerializedName("createdTime") val createdTime: String?,
  @SerializedName("deleted") val deleted: Boolean?,
  @SerializedName("hidden") val hidden: Boolean?,
  @SerializedName("updatedTime") val updatedTime: String?,
  @SerializedName("slug") val slug: String?,
  @SerializedName("date") val date: String?,
  @SerializedName("parts") val parts: List<PartDto>?,
  @SerializedName("tags") val tags: List<TagDto>?,
  @SerializedName("disclaimer") val disclaimer: String?,
  @SerializedName("text") val text: String?,
  @SerializedName("textshort") val shortText: String?
)
