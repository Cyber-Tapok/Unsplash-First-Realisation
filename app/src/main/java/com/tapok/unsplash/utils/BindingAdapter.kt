package com.tapok.unsplash.utils

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.tapok.unsplash.model.UnsplashPhoto


@BindingAdapter("app:loadPhoto")
fun loadImage(view: ImageView, photo: UnsplashPhoto?) {
    if (photo != null) {
        val bitmap = BlurHashDecoder.decode(photo.blurHash, photo.width/10, photo.height/10)
        val drawable: Drawable = BitmapDrawable(view.resources, bitmap)
        Picasso.get().load(photo.urls.regular).placeholder(drawable).into(view)
    }
}