package com.tapok.unsplash.repos

import androidx.lifecycle.MutableLiveData
import com.tapok.unsplash.model.Collections
import com.tapok.unsplash.retrofit.DataState
import com.tapok.unsplash.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CollectionsRepository {
    private val _data: MutableLiveData<DataState<Collections>> = MutableLiveData(DataState.Idle)

    val data get() = _data

    suspend fun loadData() {
        withContext(Dispatchers.IO) {
            data.postValue(DataState.Start)
            try {
                val response = RetrofitClient.unsplashService().getCollections(1)
                data.postValue(DataState.Success(response))
            } catch (e: Exception) {
                data.postValue(DataState.Failed(e))
            }
        }
    }

}