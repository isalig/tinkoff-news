package io.aiico.tnews.data.api

import com.google.gson.annotations.SerializedName
import io.aiico.tnews.data.model.ArticleDto

data class NewResponseContentDto(
    @SerializedName("news") val news: List<ArticleDto>,
    @SerializedName("title") val total: Int,
)
