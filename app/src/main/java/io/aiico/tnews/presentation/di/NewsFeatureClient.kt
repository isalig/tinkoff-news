package io.aiico.tnews.presentation.di

import io.aiico.tnews.presentation.di.component.NewsFeatureComponent

interface NewsFeatureClient {

  fun dispatchInjection(component: NewsFeatureComponent)
}
