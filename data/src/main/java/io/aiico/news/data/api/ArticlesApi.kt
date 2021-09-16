package io.aiico.news.data.api

import io.aiico.news.data.model.NewsResponse
import retrofit2.http.GET

interface ArticlesApi {

  @GET("getArticles")
  suspend fun getNewsTitles(): NewsResponse
}
