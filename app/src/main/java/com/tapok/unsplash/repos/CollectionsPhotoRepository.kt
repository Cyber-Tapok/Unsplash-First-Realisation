package com.tapok.unsplash.repos

import androidx.paging.Pager
import androidx.paging.liveData
import com.tapok.unsplash.paging.CollectionPhotoPagingSource
import com.tapok.unsplash.paging.DefaultPagingConfig.Companion.pagingConfig

class CollectionsPhotoRepository {

    fun loadData(id: String) = Pager(
        config = pagingConfig,
        pagingSourceFactory = { CollectionPhotoPagingSource(id) }).liveData

}