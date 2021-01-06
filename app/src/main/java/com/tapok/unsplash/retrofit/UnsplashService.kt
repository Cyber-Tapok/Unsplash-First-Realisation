package com.tapok.unsplash.retrofit

import com.tapok.unsplash.model.Collections
import com.tapok.unsplash.model.CollectionsPhoto
import com.tapok.unsplash.model.UnsplashPhoto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashService {


    @Headers("Accept-Version: v1")
    @GET("photos/random")
    suspend fun getRandomImage(@Query("client_id") clientId: String = CLIENT_ID): UnsplashPhoto

    @Headers("Accept-Version: v1")
    @GET("collections")
    suspend fun getCollections(
        @Query("page") page: Int,
        @Query("client_id") clientId: String = CLIENT_ID,
        @Query("per_page") perPage: Int = PER_PAGE_DEFAULT
    ): Collections

    @Headers("Accept-Version: v1")
    @GET("collections/{id}/photos")
    suspend fun getCollectionsPhoto(
        @Path("id") id: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = PER_PAGE_DEFAULT,
        @Query("client_id") clientId: String = CLIENT_ID,
    ): CollectionsPhoto

    companion object {
        private const val CLIENT_ID = "3I_6NhCRh9X-9NCsOyqJ3Hhq912jJq8VUhOOQtcQZu8"
        private const val PER_PAGE_DEFAULT = 20
    }
}