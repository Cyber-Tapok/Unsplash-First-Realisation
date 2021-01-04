package com.tapok.unsplash.model

import com.google.gson.annotations.SerializedName

data class UnsplashPhoto(
    @SerializedName("id") val id: String,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("blur_hash") val blurHash: String,
    @SerializedName("alt_description") val alt_description: String,
    @SerializedName("urls") val urls: Urls,
)
