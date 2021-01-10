package com.tapok.unsplash.model

import com.google.gson.annotations.SerializedName

data class SearchResult(
    val total: Int,
    @SerializedName("total_pages") val totalPages: Int,
    val results: CollectionsPhoto
)
