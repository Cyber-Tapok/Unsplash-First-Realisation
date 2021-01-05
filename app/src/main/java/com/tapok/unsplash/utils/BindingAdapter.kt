package com.tapok.unsplash.utils

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import com.tapok.unsplash.model.UnsplashPhoto


@BindingAdapter("loadPhoto")
fun loadImage(view: ImageView, photo: UnsplashPhoto?) {
    if (photo != null) {
        val bitmap = BlurHashDecoder.decode(
            photo.blurHash,
            photo.width / RESIZE_MULTIPLIER,
            photo.height / RESIZE_MULTIPLIER
        )
        val drawable: Drawable = BitmapDrawable(view.resources, bitmap)
        Picasso.get().load(photo.urls.regular).placeholder(drawable).into(view)
    }
}

@BindingAdapter("loadPhotoThumb")
fun loadPhotoThumb(view: ImageView, photo: UnsplashPhoto?) {
    if (photo != null) {
        val bitmap = BlurHashDecoder.decode(
            photo.blurHash,
            photo.width / RESIZE_MULTIPLIER,
            photo.height / RESIZE_MULTIPLIER
        )
        val drawable: Drawable = BitmapDrawable(view.resources, bitmap)
        Picasso.get()
            .load(photo.urls.thumb)
            .placeholder(drawable)
            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            .into(view)
    }
}

@BindingAdapter("htmlText")
fun setHtmlTextValue(textView: TextView, htmlText: String) {
    textView.text = HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_LEGACY)
}

@BindingAdapter("descriptionVisibility")
fun setDescriptionVisibility(textView: TextView, description: String?) {
    textView.isVisible = !description.isNullOrEmpty()
}

private const val RESIZE_MULTIPLIER = 10