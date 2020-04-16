package io.aiico.tnews.detailed

import dagger.BindsInstance
import dagger.Component
import io.aiico.tnews.CommonDependencies

@Component(dependencies = [CommonDependencies::class])
interface DetailedNewsComponent {

    fun inject(detailsFragment: DetailedNewsFragment)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun setNewsId(id: String): Builder

        fun setDependencies(dependencies: CommonDependencies): Builder

        fun build(): DetailedNewsComponent
    }

    companion object {
        fun create(newsId: String, dependencies: CommonDependencies): DetailedNewsComponent =
            DaggerDetailedNewsComponent
                .builder()
                .setNewsId(newsId)
                .setDependencies(dependencies)
                .build()
    }
}
