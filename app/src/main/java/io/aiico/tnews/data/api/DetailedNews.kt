package io.aiico.tnews.data.api

data class DetailedNews(
    val title: NewsTitle,
    val creationDate: NewsDate,
    val lasModificationDate: NewsDate,
    val content: String,
    val bankInfoTypeId: Int,
    val typeId: String
)
