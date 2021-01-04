package com.tapok.unsplash.retrofit

import com.tapok.unsplash.model.UnsplashPhoto
import retrofit2.http.GET
import retrofit2.http.Headers

interface UnsplashService {


    @Headers("Accept-Version: v1")
    @GET("random?client_id=${CLIENT_ID}")
    suspend fun getRandomImage() : UnsplashPhoto

    companion object {
        private const val CLIENT_ID = "3I_6NhCRh9X-9NCsOyqJ3Hhq912jJq8VUhOOQtcQZu8"
    }
}