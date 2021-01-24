package io.aiico.tnews.presentation.di.component

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import io.aiico.tnews.presentation.di.CommonDependencies
import io.aiico.news.data.ApiModule
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

  @Component.Factory
  interface Factory {
    fun create(@BindsInstance context: Context): AppComponent
  }

  companion object {
    fun create(context: Context): AppComponent = DaggerAppComponent.factory().create(context)
  }
}
