package com.tapok.unsplash.repos

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.tapok.unsplash.paging.CollectionPhotoPagingSource
import com.tapok.unsplash.paging.CollectionsPagingSource

class CollectionsPhotoRepository {

    fun loadData(id: String) = Pager(
        config = pagingConfig,
        pagingSourceFactory = { CollectionPhotoPagingSource(id) }).liveData

    private val pagingConfig: PagingConfig = PagingConfig(
        pageSize = 20,
        initialLoadSize = 20,
        enablePlaceholders = true
    )
}