package io.aiico.tnews.data.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("getArticles")
    fun getNewsTitles(): Single<NewsResponse>
}
