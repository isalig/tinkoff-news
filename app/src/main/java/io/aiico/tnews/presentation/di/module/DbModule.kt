package io.aiico.tnews.presentation.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import io.aiico.tnews.data.database.NewsDao
import io.aiico.tnews.data.database.NewsDb
import javax.inject.Singleton

@Module
object DbModule {

    @JvmStatic
    @Provides
    fun provideDatabase(context: Context): NewsDb = NewsDb.create(context)

    @Singleton
    @JvmStatic
    @Provides
    fun provideNewsDao(database: NewsDb): NewsDao = database.newsDao
}