package io.aiico.tnews.data.api

data class NewsResponse<T>(
    val resultCode: String,
    val payload: T,
    val trackingId: String
)