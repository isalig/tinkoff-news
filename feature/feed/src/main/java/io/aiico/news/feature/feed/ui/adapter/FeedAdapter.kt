package io.aiico.news.feature.feed.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.aiico.news.feature.feed.databinding.ListItemNewsTitleBinding
import io.aiico.news.shared.editorial.domain.model.Article

class FeedAdapter(private inline val onClick: (id: String) -> Unit) :
  RecyclerView.Adapter<FeedViewHolder>() {

  private var titles: List<Article> = emptyList()

  fun submitList(newNewsTitles: List<Article>) {
    val isInitial = titles.isEmpty()
    titles = newNewsTitles
    if (isInitial) notifyItemRangeInserted(0, titles.size)
    else notifyItemRangeChanged(0, titles.size)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    return FeedViewHolder(ListItemNewsTitleBinding.inflate(layoutInflater, parent, false))
  }

  override fun onViewAttachedToWindow(holder: FeedViewHolder) {
    holder.itemView.setOnClickListener {
      onClick.invoke(titles[holder.bindingAdapterPosition].id)
    }
  }

  override fun onViewDetachedFromWindow(holder: FeedViewHolder) {
    holder.itemView.setOnClickListener(null)
  }

  override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
    holder.bind(titles[position])
  }

  override fun getItemCount(): Int = titles.size
}
