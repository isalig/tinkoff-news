package io.aiico.tnews.presentation.di.component

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import io.aiico.tnews.presentation.di.CommonDependencies
import io.aiico.tnews.presentation.di.module.ApiModule
import io.aiico.tnews.presentation.di.module.NavigationModule
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    ApiModule::class,
    NavigationModule::class
  ]
)
interface AppComponent : CommonDependencies {

  @Component.Builder
  interface Builder {

    @BindsInstance
    fun setContext(context: Context): Builder

    fun build(): AppComponent
  }

  companion object {

    fun create(context: Context): AppComponent =
      DaggerAppComponent.builder()
        .setContext(context)
        .build()
  }
}
