package io.aiico.tnews

data class NewsTitle(
    val id: String,
    val name: String,
    val text: String,
    val publicationDate: NewsDate,
    val bankInfoTypeId: Int
)