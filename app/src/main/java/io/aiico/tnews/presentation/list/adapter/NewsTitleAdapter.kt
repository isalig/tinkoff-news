package io.aiico.tnews.presentation.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.aiico.tnews.domain.News
import io.aiico.tnews.R
import io.aiico.tnews.presentation.inflateViewHolder

class NewsTitleAdapter(private inline val onClick: (id: String) -> Unit) : RecyclerView.Adapter<NewsTitleViewHolder>() {

    private val titles = ArrayList<News>()

    fun submitList(newNewsTitles: List<News>) {
        titles.clear()
        titles.addAll(newNewsTitles)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsTitleViewHolder =
        NewsTitleViewHolder(parent.inflateViewHolder(R.layout.list_item_news_title))

    override fun onViewAttachedToWindow(holder: NewsTitleViewHolder) {
        holder.itemView.setOnClickListener {
            onClick.invoke(titles[holder.adapterPosition].id)
        }
    }

    override fun onViewDetachedFromWindow(holder: NewsTitleViewHolder) {
        holder.itemView.setOnClickListener(null)
    }

    override fun onBindViewHolder(holder: NewsTitleViewHolder, position: Int) {
        holder.bind(titles[position])
    }

    override fun getItemCount(): Int = titles.size
}
