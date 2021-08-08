package io.aiico.tnews.presentation.detailed

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import io.aiico.news.domain.model.Article
import io.aiico.tnews.R
import io.aiico.tnews.presentation.NewsApp
import io.aiico.tnews.presentation.asSpannedHtml
import io.aiico.tnews.presentation.showToast
import kotlinx.android.synthetic.main.fragment_detailed_news.*
import kotlinx.android.synthetic.main.list_item_news_title.*

private const val KEY_NEWS_ID = "news_id"

class ArticleFragment : Fragment(R.layout.fragment_detailed_news), ArticleView {

  private lateinit var presenter: ArticlePresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    presenter = ArticleComponent
      .create(
        requireNotNull(requireArguments().getString(KEY_NEWS_ID)),
        (requireActivity().application as NewsApp).appComponent
      )
      .presenter
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    detailsToolbar.setNavigationOnClickListener {
      activity?.onBackPressed()
    }
    detailsSwipeRefreshLayout.setOnRefreshListener {
      presenter.onRefresh()
    }

    presenter.attachView(this)
  }

  @Suppress("DEPRECATION")
  override fun showArticle(article: Article) {
    with(article) {
      newsTitleTextView.text = title
      newsDateTextView.text = createdTime
      newsContentTextView.text = text.asSpannedHtml()
    }
  }

  override fun showError() {
    context?.showToast(R.string.loading_failed_message)
  }

  override fun showLoading(isLoading: Boolean) {
    detailsSwipeRefreshLayout.isRefreshing = isLoading
  }

  override fun onDestroyView() {
    super.onDestroyView()
    presenter.detachView()
  }

  companion object {

    fun newInstance(id: String) =
      ArticleFragment().apply { arguments = bundleOf(KEY_NEWS_ID to id) }
  }
}
