package io.aiico.news.shared

import android.graphics.Rect
import android.view.View
import androidx.annotation.Px
import androidx.recyclerview.widget.RecyclerView

class ItemsVerticalOffsetDecoration(
  @Px private val verticalOffset: Int,
  private val offsetDirection: OffsetDirection = OffsetDirection.TOP
) : RecyclerView.ItemDecoration() {

  override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
    super.getItemOffsets(outRect, view, parent, state)
    val adapterPosition = parent.getChildAdapterPosition(view)
    if (adapterPosition < 0) return
    when (offsetDirection) {
      OffsetDirection.TOP -> addTopOffset(outRect, adapterPosition)
      OffsetDirection.BOTTOM -> addBottomOffset(outRect, parent, adapterPosition)
    }
  }

  private fun addTopOffset(outRect: Rect, adapterPosition: Int) {
    if (adapterPosition > 0) {
      outRect.top = verticalOffset
    }
  }

  private fun addBottomOffset(outRect: Rect, parent: RecyclerView, adapterPosition: Int) {
    if (adapterPosition < requireNotNull(parent.adapter).itemCount) {
      outRect.bottom = verticalOffset
    }
  }

  enum class OffsetDirection {
    TOP,
    BOTTOM
  }
}
