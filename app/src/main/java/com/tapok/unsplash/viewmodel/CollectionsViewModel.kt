package com.tapok.unsplash.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapok.unsplash.repos.CollectionsRepository
import kotlinx.coroutines.launch

class CollectionsViewModel : ViewModel() {

    private val repository = CollectionsRepository()

    private val _data = repository.data

    val data get() = _data

    val isRefreshing = ObservableBoolean()

    fun loadData() {
        isRefreshing.set(true)
        viewModelScope.launch {
            repository.loadData()
            isRefreshing.set(false)
        }
    }
}