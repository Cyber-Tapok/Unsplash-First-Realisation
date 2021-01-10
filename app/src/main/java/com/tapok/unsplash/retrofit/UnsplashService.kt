package com.tapok.unsplash.retrofit

import com.tapok.unsplash.BuildConfig
import com.tapok.unsplash.model.Collections
import com.tapok.unsplash.model.CollectionsPhoto
import com.tapok.unsplash.model.SearchResult
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
        @Query("per_page") perPage: Int = PER_PAGE_DEFAULT,
        @Query("client_id") clientId: String = CLIENT_ID,
    ): Collections

    @Headers("Accept-Version: v1")
    @GET("collections/{id}/photos")
    suspend fun getCollectionsPhoto(
        @Path("id") id: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = PER_PAGE_DEFAULT,
        @Query("client_id") clientId: String = CLIENT_ID,
    ): CollectionsPhoto

    @Headers("Accept-Version: v1")
    @GET("/search/photos")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("client_id") clientId: String = CLIENT_ID,
    ): SearchResult

    companion object {
        private const val CLIENT_ID = BuildConfig.ACCESS_KEY
        private const val PER_PAGE_DEFAULT = 20
    }
}