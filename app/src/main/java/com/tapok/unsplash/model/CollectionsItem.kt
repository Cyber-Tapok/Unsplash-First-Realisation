package com.tapok.unsplash.model

data class CollectionsItem(
    val id: String,
    val cover_photo: UnsplashPhoto,
    val description: String,
    val title: String,
    val total_photos: Int,
    val user: User
)