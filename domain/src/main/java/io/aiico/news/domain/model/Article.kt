package io.aiico.news.domain.model

data class Article(
        val id: String,
        val title: String,
        val image: String?,
        val lang: String?,
        val createdTime: String?,
        val deleted: Boolean,
        val hidden: Boolean,
        val updatedTime: String?,
        val slug: String?,
        val date: String?,
        val parts: List<Part>,
        val tags: List<Tag>,
        val disclaimer: String,
        val text: String,
        val shortText: String
)
