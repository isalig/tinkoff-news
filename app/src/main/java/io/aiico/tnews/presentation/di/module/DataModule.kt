package io.aiico.tnews.presentation.di.module

import dagger.Binds
import dagger.Module
import io.aiico.news.domain.ArticlesRepository
import io.aiico.tnews.data.ArticlesRepositoryImpl

@Module
abstract class DataModule {

  @Binds
  abstract fun bindArticlesRepository(repository: ArticlesRepositoryImpl): ArticlesRepository
}
