package io.aiico.news.data.api

import io.aiico.news.data.model.NewsResponse
import io.reactivex.Single

interface NewsApi {

  fun getNewsTitles(): Single<NewsResponse>
}
