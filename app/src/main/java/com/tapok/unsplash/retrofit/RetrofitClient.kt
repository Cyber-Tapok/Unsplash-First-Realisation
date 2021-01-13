package com.tapok.unsplash.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private const val BASE_URL = "https://api.unsplash.com/"

    private val unsplashRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun unsplashService(): UnsplashService = unsplashRetrofit.create(UnsplashService::class.java)
}