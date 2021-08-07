package io.aiico.news.domain.model

data class Article(
  val id: String,
  val title: String,
  val image: String?,
  val createdTime: String?,
  val hidden: Boolean,
  val slug: String?,
  val parts: List<Part>,
  val tags: List<Tag>,
  val text: String,
  val shortText: String
)
