package com.tapok.unsplash.utils

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.tapok.unsplash.model.UnsplashPhoto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@BindingAdapter("loadPhoto")
fun ImageView.loadImage(photo: UnsplashPhoto?) {
    if (photo != null) {
        layout(0, 0, 0, 0)
        GlobalScope.launch(Dispatchers.IO) {
            val bitmap = BlurHashDecoder.decode(
                photo.blurHash,
                photo.width / RESIZE_MULTIPLIER,
                photo.height / RESIZE_MULTIPLIER
            )
            val drawable: Drawable = BitmapDrawable(this@loadImage.resources, bitmap)
            withContext(Dispatchers.Main) {
                Glide.with(this@loadImage)
                    .load(photo.urls.regular)
                    .override(SIZE_ORIGINAL / 2, SIZE_ORIGINAL / 2)
                    .placeholder(drawable)
                    .into(this@loadImage)
            }
        }
    }
}

@BindingAdapter("htmlText")
fun TextView.setHtmlTextValue(htmlText: String) {
    text = HtmlCompat.fromHtml(htmlText, HtmlCompat.FROM_HTML_MODE_LEGACY)
}

@BindingAdapter("descriptionVisibility")
fun TextView.setDescriptionVisibility(description: String?) {
    isVisible = !description.isNullOrEmpty()
}

private const val RESIZE_MULTIPLIER = 100