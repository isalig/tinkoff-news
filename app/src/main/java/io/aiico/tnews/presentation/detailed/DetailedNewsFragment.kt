package io.aiico.tnews.presentation.detailed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.aiico.tnews.presentation.di.NewsFeatureClient
import io.aiico.tnews.presentation.di.component.NewsFeatureComponent
import io.aiico.tnews.R
import io.aiico.tnews.domain.model.Article
import io.aiico.tnews.presentation.asSpannedHtml
import io.aiico.tnews.presentation.di.component.DetailedNewsComponent
import io.aiico.tnews.presentation.showToast
import kotlinx.android.synthetic.main.fragment_detailed_news.*
import kotlinx.android.synthetic.main.list_item_news_title.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject
import javax.inject.Provider

class DetailedNewsFragment : MvpAppCompatFragment(), DetailedNewsView,
    NewsFeatureClient {

    @Inject
    lateinit var presenterProvider: Provider<DetailedNewsPresenter>
    private val presenter: DetailedNewsPresenter by moxyPresenter {
        presenterProvider.get()
    }

    override fun dispatchInjection(component: NewsFeatureComponent) {
        DetailedNewsComponent
            .create(arguments?.getString(KEY_NEWS_ID)!!, component)
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detailed_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsToolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        detailsSwipeRefreshLayout.setOnRefreshListener {
            presenter.onRefresh()
        }
    }

    @Suppress("DEPRECATION")
    override fun showNewsDetails(article: Article) {
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

    companion object {

        private const val KEY_NEWS_ID = "news_id"

        fun newInstance(id: String) =
            DetailedNewsFragment().apply {
                arguments = packArguments(id)
            }

        private fun packArguments(id: String) =
            Bundle().apply {
                putString(KEY_NEWS_ID, id)
            }
    }
}
