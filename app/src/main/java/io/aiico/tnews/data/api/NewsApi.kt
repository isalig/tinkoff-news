package io.aiico.tnews.data.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("news")
    fun getNewsTitles(): Single<NewsResponse<List<NewsTitle>>>

    @GET("news_content")
    fun getDetailedNews(@Query("id") id: String): Single<NewsResponse<DetailedNews>>
}