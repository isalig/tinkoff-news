package io.aiico.news.shared

import android.content.Context
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.DimenRes
import androidx.annotation.LayoutRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

fun ViewGroup.inflateView(@LayoutRes layoutResId: Int): View =
  LayoutInflater
    .from(context)
    .inflate(layoutResId, this, false)

@Suppress("DEPRECATION")
fun String.asSpannedHtml(): Spanned =
  if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
    Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
  } else {
    Html.fromHtml(this)
  }

fun Context.showToast(messageResId: Int) {
  showToast(getString(messageResId))
}

fun Context.showToast(message: String) {
  Toast
    .makeText(this, message, Toast.LENGTH_SHORT)
    .show()
}

fun <T> Flow<T>.launchWhenStarted(lifecycleOwner: LifecycleOwner) {
  lifecycleOwner.lifecycleScope.launchWhenStarted {
    collect()
  }
}

fun View.getSize(@DimenRes dimenResId: Int): Int = resources.getDimensionPixelSize(dimenResId)
