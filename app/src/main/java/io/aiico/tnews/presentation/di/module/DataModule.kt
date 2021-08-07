package io.aiico.tnews.presentation.di.module

import dagger.Binds
import dagger.Module
import io.aiico.news.data.ArticlesRepositoryImpl
import io.aiico.news.domain.repository.ArticlesRepository

@Module
interface DataModule {

  @Binds
  fun bindArticlesRepository(repository: ArticlesRepositoryImpl): ArticlesRepository
}
