package com.tapok.unsplash.paging

import androidx.paging.PagingConfig

class DefaultPagingConfig {
    companion object {
        val pagingConfig: PagingConfig = PagingConfig(
            pageSize = 20,
            initialLoadSize = 20,
            enablePlaceholders = false
        )
    }
}