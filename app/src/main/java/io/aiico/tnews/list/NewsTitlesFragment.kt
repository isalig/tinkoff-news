package io.aiico.tnews.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import io.aiico.tnews.NewsFeatureClient
import io.aiico.tnews.NewsFeatureComponent
import io.aiico.tnews.NewsNavigator
import io.aiico.tnews.NewsTitle
import io.aiico.tnews.R
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
        (activity as? NewsNavigator)?.navigateToDetails(id)
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
    }

    private fun initTitlesRecyclerView() {
        val dividerDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        newsTitlesRecyclerView.addItemDecoration(dividerDecoration)
        newsTitlesRecyclerView.adapter = newsTitleAdapter
    }

    override fun showNewsTitles(titles: List<NewsTitle>) {
        newsTitleAdapter.submitList(titles)
    }

    override fun showError() {
        Snackbar.make(newsTitlesRecyclerView, getString(R.string.error_message), Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.retry) {
                presenter.onRefresh()
            }.show()
    }

    override fun showLoading(isLoading: Boolean) {
        titlesRefreshLayout.isRefreshing = isLoading
    }

    companion object {

        fun newInstance() = NewsTitlesFragment()
    }
}
