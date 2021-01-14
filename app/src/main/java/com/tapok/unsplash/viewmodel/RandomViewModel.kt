package com.tapok.unsplash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapok.unsplash.repos.RandomRepository
import kotlinx.coroutines.launch

class RandomViewModel : ViewModel() {
    private val repository = RandomRepository()

    private val _data = repository.data
    val data get() = _data

    fun loadData() {
        viewModelScope.launch {
            repository.loadData()
        }
    }

}