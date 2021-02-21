package io.aiico.tnews.presentation.di.module

import dagger.Binds
import dagger.Module
import io.aiico.news.data.ArticlesRepositoryImpl
import io.aiico.news.domain.repository.ArticlesRepository

@Module
abstract class DataModule {

  @Binds
  abstract fun bindArticlesRepository(repository: ArticlesRepositoryImpl): ArticlesRepository
}
