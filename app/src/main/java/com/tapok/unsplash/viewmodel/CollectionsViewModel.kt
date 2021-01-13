package com.tapok.unsplash.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.tapok.unsplash.repos.CollectionsRepository
import kotlinx.coroutines.launch

class CollectionsViewModel : ViewModel() {

    private val repository = CollectionsRepository()

    private val _data = repository.loadData().cachedIn(viewModelScope)

    val data get() = _data

    val isRefreshing = ObservableBoolean()
}