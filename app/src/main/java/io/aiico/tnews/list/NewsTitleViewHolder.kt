package io.aiico.tnews.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.aiico.tnews.NewsTitle
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_news_title.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

class NewsTitleViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private val dateFormat = SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault())

    fun bind(title: NewsTitle) {
        newsTitleTextView.text = title.text
        newsDateTextView.text = dateFormat.format(Date(title.publicationDate.milliseconds))
    }
}
