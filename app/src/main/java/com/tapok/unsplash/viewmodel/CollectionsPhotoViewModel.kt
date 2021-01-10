package com.tapok.unsplash.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.tapok.unsplash.repos.CollectionsPhotoRepository

class CollectionsPhotoViewModel : ViewModel() {

    private val repository = CollectionsPhotoRepository()

    private val currentQuery: MutableLiveData<String> = MutableLiveData()

    private val _data =
        currentQuery.switchMap { id -> repository.loadData(id).cachedIn(viewModelScope) }

    val data get() = _data

    fun searchPhotos(id: String) {
        currentQuery.value = id
    }


    val isRefreshing = ObservableBoolean()
}