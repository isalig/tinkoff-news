package io.aiico.news.feature.feed.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.aiico.news.shared.editorial.domain.model.Article
import org.threeten.bp.format.DateTimeFormatter

class FeedViewHolder(
  private val view: View
) : RecyclerView.ViewHolder(view) {

  private val dateFormatter = DateTimeFormatter.ofPattern("HH:mm dd/MMM/yyyy")

  fun bind(article: Article) {
//    with(article) {
//      newsTitleTextView.text = title.asSpannedHtml()
//      newsDateTextView.text = publicationDateTime?.format(dateFormatter)
//    }
  }
}
