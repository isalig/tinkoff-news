package io.aiico.news.shared.di

import android.app.Activity
import androidx.fragment.app.Fragment

interface Dependencies

interface DependenciesProvider {

  fun getDependencies(): Any
}

inline fun <reified T : Dependencies> DependenciesProvider.hasDependencies(): Boolean =
  getDependencies() is T

inline fun <reified T : Dependencies> DependenciesProvider.requireDependencies(): T =
  getDependencies() as T

inline fun <reified T : Dependencies> Fragment.findDependencies(): T {
  var parentFragment = parentFragment
  while (parentFragment != null) {
    if (parentFragment is DependenciesProvider && parentFragment.hasDependencies<T>()) {
      return parentFragment.requireDependencies()
    }
    parentFragment = parentFragment.parentFragment
  }

  val parentActivity = activity
  if (parentActivity is DependenciesProvider && parentActivity.hasDependencies<T>()) {
    return parentActivity.requireDependencies()
  }

  val parentApp = activity?.application
  if (parentApp is DependenciesProvider && parentApp.hasDependencies<T>()) {
    return parentApp.requireDependencies()
  }

  error("Couldn't find dependencies")
}

inline fun <reified T : Dependencies> Activity.findDependencies(): T {
  val parentApp = application
  if (parentApp is DependenciesProvider && parentApp.hasDependencies<T>()) {
    return parentApp.requireDependencies()
  }

  error("Couldn't find dependencies")
}
