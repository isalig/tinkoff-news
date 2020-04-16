package io.aiico.tnews

import dagger.Component
import io.aiico.tnews.detailed.DetailedNewsFragment
import io.aiico.tnews.list.NewsTitlesFragment

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
            DaggerNewsFeatureComponent
                .builder()
                .setDependencies(dependencies)
                .build()
    }
}