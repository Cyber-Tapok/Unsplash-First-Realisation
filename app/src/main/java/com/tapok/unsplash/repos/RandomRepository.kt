package com.tapok.unsplash.repos

import androidx.lifecycle.MutableLiveData
import com.tapok.unsplash.model.UnsplashPhoto
import com.tapok.unsplash.retrofit.DataState
import com.tapok.unsplash.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RandomRepository {
    private val _data: MutableLiveData<DataState<UnsplashPhoto>> = MutableLiveData(DataState.Idle)

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
    }

}