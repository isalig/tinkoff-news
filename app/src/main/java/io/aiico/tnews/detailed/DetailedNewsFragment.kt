package io.aiico.tnews.detailed

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import io.aiico.tnews.DetailedNews
import io.aiico.tnews.NewsFeatureClient
import io.aiico.tnews.NewsFeatureComponent
import io.aiico.tnews.R
import kotlinx.android.synthetic.main.fragment_detailed_news.*
import kotlinx.android.synthetic.main.list_item_news_title.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import javax.inject.Provider

class DetailedNewsFragment : MvpAppCompatFragment(), DetailedNewsView, NewsFeatureClient {

    private val dateFormat = SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault())

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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
    override fun showNewsDetails(news: DetailedNews) {
        newsTitleTextView.text = news.title.text
        newsDateTextView.text = dateFormat.format(Date(news.title.publicationDate.milliseconds))
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            newsContentTextView.text = Html.fromHtml(news.content, Html.FROM_HTML_MODE_LEGACY)
        } else {
            newsContentTextView.text = Html.fromHtml(news.content)
        }
    }

    override fun showError() {
        Snackbar.make(detailsContentLayout, getString(R.string.error_message), Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.retry) {
                presenter.onRefresh()
            }.show()
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