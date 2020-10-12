package io.aiico.tnews.data.model

import com.google.gson.annotations.SerializedName

data class NewResponseContentDto(
  @SerializedName("news") val news: List<ArticleDto>,
  @SerializedName("total") val total: Int,
)
