package io.aiico.tnews

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class NewsInteractor @Inject constructor(private val newsApi: NewsApi) {

    fun getAllNews(): Single<List<NewsTitle>> =
        newsApi
            .getNewsTitles()
            .map { response -> response.payload }
            .observeOn(AndroidSchedulers.mainThread())

    fun getDetailedNews(id: String): Single<DetailedNews> =
        newsApi
            .getDetailedNews(id)
            .map { response -> response.payload }
            .observeOn(AndroidSchedulers.mainThread())
}