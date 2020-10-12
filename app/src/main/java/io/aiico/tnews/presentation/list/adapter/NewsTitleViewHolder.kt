package io.aiico.tnews.presentation.list.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.aiico.tnews.domain.model.Article
import io.aiico.tnews.presentation.asSpannedHtml
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_news_title.*
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NewsTitleViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(article: Article) {
        with(article) {
            newsTitleTextView.text = title.asSpannedHtml()
            newsDateTextView.text = createdTime
        }
    }
}
