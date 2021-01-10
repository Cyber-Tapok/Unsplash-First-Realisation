package com.tapok.unsplash.paging

import androidx.paging.PagingSource
import com.tapok.unsplash.model.CollectionsItem
import com.tapok.unsplash.retrofit.RetrofitClient

class CollectionsPagingSource : PagingSource<Int, CollectionsItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CollectionsItem> {
        val position = params.key ?: STARTING_PAGE

        return try {
            val response =
                RetrofitClient.unsplashService().getCollections(position, params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = if (position == STARTING_PAGE) null else position - 1,
                nextKey = if (response.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val STARTING_PAGE = 1
    }
}