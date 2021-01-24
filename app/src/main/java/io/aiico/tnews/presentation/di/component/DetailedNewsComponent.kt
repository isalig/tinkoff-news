package io.aiico.tnews.presentation.di.component

import dagger.BindsInstance
import dagger.Component
import io.aiico.tnews.presentation.detailed.DetailedNewsFragment
import io.aiico.tnews.presentation.di.CommonDependencies
import io.aiico.tnews.presentation.di.component.DaggerAppComponent.factory
import io.aiico.tnews.presentation.di.module.DataModule

@Component(
  dependencies = [CommonDependencies::class],
  modules = [DataModule::class]
)
interface DetailedNewsComponent {

  fun inject(detailsFragment: DetailedNewsFragment)

  @Component.Factory
  interface Factory {
    fun create(
      @BindsInstance id: String,
      dependencies: CommonDependencies
    ): DetailedNewsComponent
  }

  companion object {

    fun create(newsId: String, dependencies: CommonDependencies): DetailedNewsComponent =
      DaggerDetailedNewsComponent.factory()
        .create(newsId, dependencies)
  }
}
