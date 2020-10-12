package io.aiico.tnews.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.aiico.tnews.R
import io.aiico.tnews.presentation.di.NewsFeatureClient
import io.aiico.tnews.presentation.di.component.NewsFeatureComponent
import io.aiico.tnews.presentation.isVisible
import io.aiico.tnews.presentation.list.adapter.NewsTitleAdapter
import io.aiico.tnews.presentation.showToast
import kotlinx.android.synthetic.main.fragment_news_titles.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class NewsTitlesFragment : MvpAppCompatFragment(), NewsTitlesView, NewsFeatureClient {

  @Inject
  lateinit var presenterProvider: Provider<NewsTitlesPresenter>
  private val presenter: NewsTitlesPresenter by moxyPresenter {
    presenterProvider.get()
  }

  private val newsTitleAdapter = NewsTitleAdapter { id ->
    presenter.onTitleClick(id)
  }

  override fun dispatchInjection(component: NewsFeatureComponent) {
    component.inject(this)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_news_titles, container, false)
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

  companion object {

    fun newInstance() = NewsTitlesFragment()
  }
}
