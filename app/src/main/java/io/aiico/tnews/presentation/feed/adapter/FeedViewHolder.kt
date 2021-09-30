package io.aiico.tnews.presentation.feed.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.aiico.news.domain.model.Article
import io.aiico.tnews.presentation.asSpannedHtml
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_news_title.*
import org.threeten.bp.format.DateTimeFormatter

class FeedViewHolder(
  override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

  private val dateFormatter = DateTimeFormatter.ofPattern("HH:mm dd/MMM/yyyy")

  fun bind(article: Article) {
    with(article) {
      newsTitleTextView.text = title.asSpannedHtml()
      newsDateTextView.text = publicationDateTime?.format(dateFormatter)
    }
  }
}
