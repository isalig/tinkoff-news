package io.aiico.tnews.presentation.di.component

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import io.aiico.news.data.ApiModule
import io.aiico.tnews.presentation.MainActivity
import io.aiico.tnews.presentation.di.component.DetailedNewsComponent.DetailedNewsDependencies
import io.aiico.tnews.presentation.di.module.DataModule
import io.aiico.tnews.presentation.di.module.NavigationModule
import io.aiico.tnews.presentation.list.NewsTitlesFragment
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    ApiModule::class,
    NavigationModule::class,
    DataModule::class
  ]
)
interface AppComponent : DetailedNewsDependencies {

  fun inject(activity: MainActivity)
  fun inject(fragment: NewsTitlesFragment)

  @Component.Factory
  interface Factory {
    fun create(@BindsInstance context: Context): AppComponent
  }

  companion object {
    fun create(context: Context): AppComponent = DaggerAppComponent.factory().create(context)
  }
}
