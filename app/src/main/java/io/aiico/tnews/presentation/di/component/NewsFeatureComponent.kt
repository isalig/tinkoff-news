package io.aiico.tnews.presentation.di.component

import dagger.Component
import io.aiico.tnews.presentation.MainActivity
import io.aiico.tnews.presentation.di.CommonDependencies
import io.aiico.tnews.presentation.di.scope.PerFeature
import io.aiico.tnews.presentation.list.NewsTitlesFragment

@PerFeature
@Component(dependencies = [CommonDependencies::class])
interface NewsFeatureComponent : CommonDependencies {

    fun inject(activity: MainActivity)
    fun inject(listFragment: NewsTitlesFragment)

    @Component.Builder
    interface Builder {

        fun setDependencies(dependencies: CommonDependencies): Builder

        fun build(): NewsFeatureComponent
    }

    companion object {

        fun create(dependencies: CommonDependencies): NewsFeatureComponent =
            DaggerNewsFeatureComponent.builder()
                .setDependencies(dependencies)
                .build()
    }
}