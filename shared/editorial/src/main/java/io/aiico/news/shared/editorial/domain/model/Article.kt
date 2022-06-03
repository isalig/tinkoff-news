package io.aiico.news.shared.editorial.domain.model

import org.threeten.bp.ZonedDateTime

data class Article(
  val id: String,
  val title: String,
  val image: String?,
  val publicationDateTime: ZonedDateTime?,
  val hidden: Boolean,
  val slug: String?,
  val parts: List<Part>,
  val tags: List<Tag>,
  val text: String,
  val shortText: String
)
