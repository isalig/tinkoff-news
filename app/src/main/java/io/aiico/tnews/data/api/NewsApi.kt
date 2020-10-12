package io.aiico.tnews.data.api

import io.aiico.tnews.data.model.NewsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface NewsApi {

    @GET("getArticles")
    fun getNewsTitles(): Single<NewsResponse>
}
