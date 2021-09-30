package io.aiico.news.data.api

import io.aiico.news.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesApi {

  @GET("getArticles")
  suspend fun getNewsTitles(
    @Query("offset") offset: Int = 0,
    @Query("pageSize") pageSize: Int = 10
  ): NewsResponse
}
