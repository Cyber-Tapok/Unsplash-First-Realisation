package com.tapok.unsplash.model

import com.google.gson.annotations.SerializedName

data class Urls(
    @SerializedName("full") val full: String,
    @SerializedName("regular") val regular: String,
    @SerializedName("thumb") val thumb: String,
)
