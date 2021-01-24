package io.aiico.tnews.presentation.list

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.aiico.tnews.R
import io.aiico.tnews.presentation.NewsApp
import io.aiico.tnews.presentation.list.adapter.NewsTitleAdapter
import io.aiico.tnews.presentation.showToast
import kotlinx.android.synthetic.main.fragment_news_titles.*
import javax.inject.Inject

class NewsTitlesFragment : Fragment(R.layout.fragment_news_titles), NewsTitlesView {

  @Inject
  lateinit var presenter: NewsTitlesPresenter

  private val newsTitleAdapter = NewsTitleAdapter { id ->
    presenter.onTitleClick(id)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    (requireActivity().application as NewsApp).appComponent.inject(this)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initTitlesRecyclerView()
    titlesRefreshLayout.setOnRefreshListener {
      presenter.onRefresh()
    }
    retryButton.setOnClickListener {
      presenter.onRefresh()
    }
    retryEmptyButton.setOnClickListener {
      presenter.onRefresh()
    }
    presenter.attachView(this)
  }

  private fun initTitlesRecyclerView() {
    val dividerDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
    newsTitlesRecyclerView.addItemDecoration(dividerDecoration)
    newsTitlesRecyclerView.adapter = newsTitleAdapter
  }

  override fun applyState(state: NewsTitlesViewState) {
    with(state) {
      titlesRefreshLayout.isVisible = showList
      titlesRefreshLayout.isRefreshing = showLoading
      newsTitleAdapter.submitList(titles)

      errorLayout.isVisible = showEmptyError
      errorMessageTextView.text = errorMessage

      loadingPlaceholderTextView.isVisible = showEmptyLoading
      listPlaceholderLayout.isVisible = showListPlaceholder
      if (showError) {
        context?.showToast(state.errorMessage)
      }
    }
  }

  override fun onDestroyView() {
    super.onDestroyView()
    presenter.detachView()
  }

  companion object {

    fun newInstance() = NewsTitlesFragment()
  }
}
