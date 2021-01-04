package com.tapok.unsplash.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Urls(
    @SerializedName("full") val full: String,
    @SerializedName("regular") val regular: String,
    @SerializedName("thumb") val thumb: String,
) : Parcelable
