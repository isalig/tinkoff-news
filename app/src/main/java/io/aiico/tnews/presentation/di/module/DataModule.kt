package io.aiico.tnews.presentation.di.module

import dagger.Binds
import dagger.Module
import io.aiico.news.shared.editorial.domain.repository.ArticlesRepository

@Module
interface DataModule {

  @Binds
  fun bindArticlesRepository(repository: io.aiico.news.shared.editorial.data.ArticlesRepositoryImpl): ArticlesRepository
}
