package com.tapok.unsplash.repos

import androidx.lifecycle.MutableLiveData
import com.tapok.unsplash.model.UnsplashPhoto
import com.tapok.unsplash.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RandomRepository {
    private val _data: MutableLiveData<DataState> = MutableLiveData(DataState.Idle)

    val data get() = _data

    suspend fun loadData() {
        withContext(Dispatchers.IO) {
            data.postValue(DataState.Start)
            try {
                val response = RetrofitClient.unsplashService().getRandomImage()
                data.postValue(DataState.Success(response))
            } catch (e: Exception) {
                data.postValue(DataState.Failed(e))
            }
        }
//        if (data.value !is DataState.Success) data.postValue(DataState.Idle)
    }

    sealed class DataState {
        object Idle : DataState()
        object Start : DataState()
        data class Success(val data: UnsplashPhoto) : DataState()
        data class Failed(val e: Exception) : DataState()
    }
}