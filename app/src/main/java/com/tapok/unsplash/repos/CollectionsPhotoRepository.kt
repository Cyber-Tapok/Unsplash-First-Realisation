package com.tapok.unsplash.repos

import androidx.lifecycle.MutableLiveData
import com.tapok.unsplash.model.CollectionsPhoto
import com.tapok.unsplash.retrofit.DataState
import com.tapok.unsplash.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CollectionsPhotoRepository {
    private val _data: MutableLiveData<DataState<CollectionsPhoto>> = MutableLiveData(DataState.Idle)

    val data get() = _data

    suspend fun loadData(idCollection: String) {
        withContext(Dispatchers.IO) {
            data.postValue(DataState.Start)
            try {
                val response = RetrofitClient.unsplashService().getCollectionsPhoto(idCollection, 1)
                data.postValue(DataState.Success(response))
            } catch (e: Exception) {
                data.postValue(DataState.Failed(e))
            }
        }
    }
}