package com.tapok.unsplash.utils

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.FutureTarget
import com.tapok.unsplash.model.UnsplashPhoto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@BindingAdapter("loadPhoto")
fun loadImage(view: ImageView, photo: UnsplashPhoto?) {
    if (photo != null) {
        GlobalScope.launch(Dispatchers.IO) {
            val bitmap = BlurHashDecoder.decode(
                photo.blurHash,
                photo.width / RESIZE_MULTIPLIER,
                photo.height / RESIZE_MULTIPLIER
            )
            val drawable: Drawable = BitmapDrawable(view.resources, bitmap)
//            withContext(Dispatchers.Main) {
//                Glide.with(view).load(drawable).placeholder(drawable).into(view)
//            }
//            val futureTarget: FutureTarget<Bitmap> =
//                Glide.with(view).asBitmap().load(photo.urls.regular).submit(
//                    photo.width / 5,
//                    photo.height / 5
//                )
//            val bit = futureTarget.get()
            withContext(Dispatchers.Main) {
                Glide.with(view).load(photo.urls.full).placeholder(drawable).into(view)
            }
        }
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