package io.aiico.news.shared.delegate

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun <T : Any> Fragment.argument(key: String? = null) = FragmentArgumentDelegate<T>(key)

class FragmentArgumentDelegate<T : Any>(private val key: String?) : ReadWriteProperty<Fragment, T> {

  @Suppress("UNCHECKED_CAST")
  override fun getValue(thisRef: Fragment, property: KProperty<*>): T = with(key ?: property.name) {
    thisRef.requireArguments().get(this) as? T ?: throw UninitializedPropertyAccessException(
      "Property $this has not been initialized."
    )
  }

  override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
    val propertyKey = key ?: property.name
    with(thisRef.arguments ?: Bundle().also(thisRef::setArguments)) {
      putAll(bundleOf(propertyKey to value))
    }
  }
}
