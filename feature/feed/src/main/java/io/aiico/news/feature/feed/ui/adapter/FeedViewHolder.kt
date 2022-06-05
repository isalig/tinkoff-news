package io.aiico.news.feature.feed.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import io.aiico.news.feature.feed.databinding.ListItemNewsTitleBinding
import io.aiico.news.shared.asSpannedHtml
import io.aiico.news.shared.editorial.domain.model.Article
import org.threeten.bp.format.DateTimeFormatter

class FeedViewHolder(
  private val binding: ListItemNewsTitleBinding,
) : RecyclerView.ViewHolder(binding.root) {

  private val dateFormatter = DateTimeFormatter.ofPattern("HH:mm dd/MMM/yyyy")

  fun bind(article: Article) {
    with(article) {
      with(binding) {
        newsTitleTextView.text = title.asSpannedHtml()
        newsDateTextView.text = publicationDateTime?.format(dateFormatter)
      }
    }
  }
}
