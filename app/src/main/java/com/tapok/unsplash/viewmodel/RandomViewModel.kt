package com.tapok.unsplash.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapok.unsplash.repos.RandomRepository
import kotlinx.coroutines.launch

class RandomViewModel: ViewModel() {
    private val repository = RandomRepository()

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