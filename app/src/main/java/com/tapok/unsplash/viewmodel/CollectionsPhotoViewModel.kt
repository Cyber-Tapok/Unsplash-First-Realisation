package com.tapok.unsplash.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapok.unsplash.repos.CollectionsPhotoRepository
import kotlinx.coroutines.launch

class CollectionsPhotoViewModel : ViewModel() {

    private val repository = CollectionsPhotoRepository()

    private val _data = repository.data

    val data get() = _data

    val isRefreshing = ObservableBoolean()

    fun loadData(idCollection: String) {
        isRefreshing.set(true)
        viewModelScope.launch {
            repository.loadData(idCollection)
            isRefreshing.set(false)
        }
    }
}