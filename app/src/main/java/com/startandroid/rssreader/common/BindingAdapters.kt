package com.startandroid.rssreader.common

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    if (url.isNotEmpty()) view.load(url) else view.setImageDrawable(null)
}