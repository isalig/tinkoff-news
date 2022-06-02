package io.aiico.news.feature.article.ui

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import io.aiico.news.feature.article.R
import io.aiico.news.shared.delegate.argument

class ArticleFragment : Fragment(R.layout.fragment_detailed_news) {

//  private lateinit var component: ArticleComponent
  private val articleId: String by argument(KEY_NEWS_ID)
//
//  private val viewModel by viewModelInstance {
//    component.factory.create(articleId)
//  }
//
//  override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    component = ArticleComponent.create(
//      requireNotNull(requireArguments().getString(KEY_NEWS_ID)),
//      (requireActivity().application as NewsApp).appComponent
//    )
//  }
//
//  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//    super.onViewCreated(view, savedInstanceState)
//    detailsToolbar.setNavigationOnClickListener {
//      activity?.onBackPressed()
//    }
//    detailsSwipeRefreshLayout.setOnRefreshListener {
//      viewModel.onRefresh()
//    }
//  }
//
//  override fun onViewStateRestored(savedInstanceState: Bundle?) {
//    super.onViewStateRestored(savedInstanceState)
//    viewModel.state
//      .onEach(::render)
//      .launchWhenStarted(viewLifecycleOwner)
//  }
//
//  private fun render(state: ViewState) {
//    detailsSwipeRefreshLayout.isRefreshing = state is Loading || state is Reloading
//    if (state is Error) context?.showToast(R.string.loading_failed_message)
//
//    if (state is Success) showArticle(state.article)
//    else if (state is Reloading) showArticle(state.article)
//  }
//
//  @Suppress("DEPRECATION")
//  private fun showArticle(article: Article) {
//    with(article) {
//      newsTitleTextView.text = title
//      newsDateTextView.text = publicationDateTime.toString()
//      newsContentTextView.text = text.asSpannedHtml()
//    }
//  }
//
  companion object {
    private const val KEY_NEWS_ID = "news_id"

    fun newInstance(id: String) = ArticleFragment().apply {
      arguments = bundleOf(KEY_NEWS_ID to id)
    }
  }
}
