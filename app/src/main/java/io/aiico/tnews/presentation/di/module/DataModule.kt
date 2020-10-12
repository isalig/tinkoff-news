package io.aiico.tnews.presentation.di.module

import dagger.Binds
import dagger.Module
import io.aiico.tnews.data.ArticlesRepositoryImpl
import io.aiico.tnews.domain.ArticlesRepository

@Module
abstract class DataModule {

    @Binds
    abstract fun bindArticlesRepository(repository: ArticlesRepositoryImpl): ArticlesRepository
}
