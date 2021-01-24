package io.aiico.tnews.presentation.di.component

import dagger.Component
import io.aiico.tnews.presentation.MainActivity
import io.aiico.tnews.presentation.di.CommonDependencies
import io.aiico.tnews.presentation.di.module.DataModule
import io.aiico.tnews.presentation.list.NewsTitlesFragment

@Component(
  dependencies = [CommonDependencies::class],
  modules = [DataModule::class]
)
interface NewsFeatureComponent : CommonDependencies {

  fun inject(activity: MainActivity)
  fun inject(listFragment: NewsTitlesFragment)

  @Component.Factory
  interface Factory {

    fun create(dependencies: CommonDependencies): NewsFeatureComponent
  }

  companion object {

    fun create(dependencies: CommonDependencies): NewsFeatureComponent =
      DaggerNewsFeatureComponent.factory()
        .create(dependencies)
  }
}
