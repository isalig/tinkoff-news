package io.aiico.tnews.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.aiico.tnews.NewsTitle
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_news_title.*
import java.util.Date

class NewsTitleViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(title: NewsTitle) {
        newsTitleTextView.text = title.text
        newsDateTextView.text = Date(title.publicationDate.milliseconds).toString()
    }
}
