package io.aiico.news.data.model

import com.google.gson.annotations.SerializedName

data class PartDto(
  @SerializedName("id") val id: String?,
  @SerializedName("title") val title: String?
)
