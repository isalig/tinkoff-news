package io.aiico.tnews.presentation.di.component

import dagger.BindsInstance
import dagger.Component
import io.aiico.news.data.api.NewsApi
import io.aiico.tnews.presentation.detailed.DetailedNewsFragment
import io.aiico.tnews.presentation.di.component.DetailedNewsComponent.DetailedNewsDependencies
import io.aiico.tnews.presentation.di.module.DataModule
import io.aiico.tnews.presentation.navigation.NewsNavigator

@Component(
  modules = [DataModule::class],
  dependencies = [DetailedNewsDependencies::class]
)
interface DetailedNewsComponent {

  fun inject(detailsFragment: DetailedNewsFragment)

  @Component.Factory
  interface Factory {
    fun create(
      @BindsInstance id: String,
      dependencies: DetailedNewsDependencies
    ): DetailedNewsComponent
  }

  companion object {

    fun create(newsId: String, dependencies: DetailedNewsDependencies): DetailedNewsComponent =
      DaggerDetailedNewsComponent.factory().create(newsId, dependencies)
  }

  interface DetailedNewsDependencies {

    fun newsApi(): NewsApi
    fun navigator(): NewsNavigator
  }
}
