package com.tapok.unsplash.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CollectionsItem(
    val id: String,
    val cover_photo: UnsplashPhoto,
    val description: String,
    val title: String,
    val total_photos: Int,
    val user: User
) : Parcelable {
    override fun toString(): String {
        return "$id/n"
    }
}