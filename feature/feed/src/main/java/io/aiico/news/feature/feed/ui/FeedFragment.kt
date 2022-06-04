package io.aiico.news.feature.feed.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.aiico.news.feature.feed.R
import io.aiico.news.feature.feed.databinding.FragmentFeedBinding
import io.aiico.news.feature.feed.di.FeedComponent
import io.aiico.news.feature.feed.ui.adapter.FeedAdapter
import io.aiico.news.shared.ItemsVerticalOffsetDecoration
import io.aiico.news.shared.ItemsVerticalOffsetDecoration.OffsetDirection
import io.aiico.news.shared.delegate.viewModelInstance
import io.aiico.news.shared.getSize
import io.aiico.news.shared.launchWhenStarted
import io.aiico.news.shared.showToast
import kotlinx.coroutines.flow.onEach

class FeedFragment : Fragment(R.layout.fragment_feed) {

  private lateinit var component: FeedComponent

  private val viewModel: FeedViewModel by viewModelInstance {
    component.factory.create()
  }

  private var binding: FragmentFeedBinding? = null

  private val newsTitleAdapter = FeedAdapter { id ->
    viewModel.onTitleClick(id)
  }

//  override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    component = FeedComponent.create((requireActivity().application as NewsApp).appComponent)
//  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initTitlesRecyclerView()
    binding?.run {
      titlesRefreshLayout.setOnRefreshListener {
        viewModel.onRefresh()
      }
      retryButton.setOnClickListener {
        viewModel.onRefresh()
      }
      retryEmptyButton.setOnClickListener {
        viewModel.onRefresh()
      }
    }
  }

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    super.onViewStateRestored(savedInstanceState)
    viewModel.state
      .onEach(::render)
      .launchWhenStarted(viewLifecycleOwner)
  }

  private fun render(state: FeedViewState) {
    binding?.run {
      with(state) {
        titlesRefreshLayout.isVisible = showList
        titlesRefreshLayout.isRefreshing = showLoading
        newsTitleAdapter.submitList(titles)
        childFragmentManager.beginTransaction().commit()

        errorLayout.isVisible = showEmptyError
        errorMessageTextView.text = errorMessage

        loadingPlaceholderTextView.isVisible = showEmptyLoading
        listPlaceholderLayout.isVisible = showListPlaceholder
        if (showError) {
          context?.showToast(state.errorMessage)
        }
      }
    }
  }

  private fun initTitlesRecyclerView() {
    binding?.run {
      listOf(
        ItemsVerticalOffsetDecoration(requireView().getSize(R.dimen.indent_m), OffsetDirection.TOP),
        ItemsVerticalOffsetDecoration(
          requireView().getSize(R.dimen.indent_m),
          OffsetDirection.BOTTOM
        ),
        DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
      ).onEach(newsTitlesRecyclerView::addItemDecoration)
      newsTitlesRecyclerView.adapter = newsTitleAdapter
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    binding = null
  }

  companion object {
    fun newInstance() = FeedFragment()
  }
}
