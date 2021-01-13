package com.tapok.unsplash.repos

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.tapok.unsplash.paging.CollectionsPagingSource
import com.tapok.unsplash.paging.DefaultPagingConfig
import com.tapok.unsplash.paging.DefaultPagingConfig.Companion.pagingConfig

class CollectionsRepository {

    fun loadData() = Pager(
        config = pagingConfig,
        pagingSourceFactory = { CollectionsPagingSource() }).liveData

}