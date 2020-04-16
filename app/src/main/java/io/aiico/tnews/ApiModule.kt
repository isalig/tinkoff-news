package io.aiico.tnews

import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import moxy.viewstate.strategy.alias.SingleState
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object ApiModule {

    @Provides
    @JvmStatic
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .baseUrl(BuildConfig.API_BASE_URL)
            .build()


    @Singleton
    @Provides
    @JvmStatic
    fun provideCurrencyApi(retrofit: Retrofit): NewsApi =
        retrofit.create(NewsApi::class.java)
}
