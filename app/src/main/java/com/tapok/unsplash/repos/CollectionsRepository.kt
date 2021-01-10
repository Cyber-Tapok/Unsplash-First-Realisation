package com.tapok.unsplash.repos

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.tapok.unsplash.paging.CollectionsPagingSource

class CollectionsRepository {

    fun loadData() = Pager(
        config = pagingConfig,
        pagingSourceFactory = { CollectionsPagingSource() }).liveData

    private val pagingConfig: PagingConfig = PagingConfig(
        pageSize = 20,
        initialLoadSize = 20,
        enablePlaceholders = false
    )
}