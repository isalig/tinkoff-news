package io.aiico.tnews.data.model

import com.google.gson.annotations.SerializedName

data class TagDto(
    @SerializedName("id") val id: String?,
    @SerializedName("value") val value: String?
)
