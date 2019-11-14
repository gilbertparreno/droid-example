package com.gp.droid.core.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(@LayoutRes layoutId: Int): View {
    return LayoutInflater.from(this.context).inflate(layoutId, this, false)
}