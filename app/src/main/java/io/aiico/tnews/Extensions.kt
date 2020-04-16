package io.aiico.tnews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

fun Disposable.addTo(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun ViewGroup.inflateViewHolder(@LayoutRes layoutResId: Int): View =
    LayoutInflater
        .from(context)
        .inflate(layoutResId, this, false)
