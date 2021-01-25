package io.aiico.news.data.api

import io.aiico.news.data.model.NewsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface RetrofitNewsApi : NewsApi {

  @GET("getArticles")
  override fun getNewsTitles(): Single<NewsResponse>
}
