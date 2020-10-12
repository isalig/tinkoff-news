package io.aiico.tnews.data.model

import com.google.gson.annotations.SerializedName

data class NewsResponse (
   @SerializedName("response") val response: NewResponseContentDto
)
