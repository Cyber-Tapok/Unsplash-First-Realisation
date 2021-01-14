package com.tapok.unsplash.paging

import androidx.paging.PagingSource
import com.tapok.unsplash.model.UnsplashPhoto
import com.tapok.unsplash.retrofit.RetrofitClient

class CollectionPhotoPagingSource(private val id: String) : PagingSource<Int, UnsplashPhoto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
        val position = params.key ?: STARTING_PAGE
        return try {
            val response = requestToApi(id, position, params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = if (position == STARTING_PAGE) null else position - 1,
                nextKey = if (response.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private suspend fun requestToApi(id: String, position: Int, loadSize: Int) =
        RetrofitClient.unsplashService().getCollectionsPhoto(id, position, loadSize)

    companion object {
        private const val STARTING_PAGE = 1
    }
}