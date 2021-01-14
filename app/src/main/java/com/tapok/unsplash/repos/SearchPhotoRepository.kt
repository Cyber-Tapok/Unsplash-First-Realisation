package com.tapok.unsplash.repos

import androidx.paging.Pager
import androidx.paging.liveData
import com.tapok.unsplash.paging.DefaultPagingConfig.Companion.pagingConfig
import com.tapok.unsplash.paging.PhotoPagingSource

class SearchPhotoRepository {

    fun loadData(query: String) = Pager(
        config = pagingConfig,
        pagingSourceFactory = { PhotoPagingSource(query) }).liveData

}