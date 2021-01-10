package com.tapok.unsplash.repos

import androidx.lifecycle.MutableLiveData
import com.tapok.unsplash.model.SearchResult
import com.tapok.unsplash.retrofit.DataState
import com.tapok.unsplash.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchPhotoRepository {
    private val _data: MutableLiveData<DataState<SearchResult>> = MutableLiveData(DataState.Idle)

    val data get() = _data

    suspend fun loadData(query: String) {
        withContext(Dispatchers.IO) {
            data.postValue(DataState.Start)
            try {
                val response = RetrofitClient.unsplashService().searchPhotos(query, 1)
                data.postValue(DataState.Success(response))
            } catch (e: Exception) {
                data.postValue(DataState.Failed(e))
            }
        }
    }
}