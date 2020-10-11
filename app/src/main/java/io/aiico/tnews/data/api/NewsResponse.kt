package io.aiico.tnews.data.api

import com.google.gson.annotations.SerializedName

data class NewsResponse (
   @SerializedName("response") val response: NewResponseContentDto
)
