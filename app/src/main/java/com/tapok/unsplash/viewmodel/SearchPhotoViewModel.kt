package com.tapok.unsplash.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapok.unsplash.repos.SearchPhotoRepository
import kotlinx.coroutines.launch

class SearchPhotoViewModel : ViewModel() {

    private val repository = SearchPhotoRepository()

    private val _data = repository.data

    val data get() = _data

    val isRefreshing = ObservableBoolean()

    fun loadData(query: String) {
        isRefreshing.set(true)
        viewModelScope.launch {
            repository.loadData(query)
            isRefreshing.set(false)
        }
    }
}