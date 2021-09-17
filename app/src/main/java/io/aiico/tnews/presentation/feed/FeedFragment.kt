package io.aiico.tnews.presentation.feed

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.aiico.tnews.R
import io.aiico.tnews.presentation.NewsApp
import io.aiico.tnews.presentation.feed.adapter.FeedAdapter
import io.aiico.tnews.presentation.launchWhenStarted
import io.aiico.tnews.presentation.showToast
import kotlinx.android.synthetic.main.fragment_feed.*
import kotlinx.coroutines.flow.onEach

class FeedFragment : Fragment(R.layout.fragment_feed) {

  private lateinit var component: FeedComponent

  private val viewModel by viewModels<FeedViewModel>(
    factoryProducer = {
      object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = component.viewModel as T
      }
    }
  )

  private val newsTitleAdapter = FeedAdapter { id ->
    viewModel.onTitleClick(id)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    component = FeedComponent.create((requireActivity().application as NewsApp).appComponent)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initTitlesRecyclerView()
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

  override fun onViewStateRestored(savedInstanceState: Bundle?) {
    super.onViewStateRestored(savedInstanceState)
    viewModel.state
      .onEach(::render)
      .launchWhenStarted(viewLifecycleOwner)
  }

  private fun render(state: FeedViewState) {
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

  private fun initTitlesRecyclerView() {
    val dividerDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
    newsTitlesRecyclerView.addItemDecoration(dividerDecoration)
    newsTitlesRecyclerView.adapter = newsTitleAdapter
  }

  companion object {
    fun newInstance() = FeedFragment()
  }
}
