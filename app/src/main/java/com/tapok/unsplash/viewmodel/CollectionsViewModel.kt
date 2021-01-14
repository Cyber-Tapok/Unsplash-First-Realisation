package com.tapok.unsplash.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.tapok.unsplash.repos.CollectionsRepository

class CollectionsViewModel : ViewModel() {

    private val repository = CollectionsRepository()

    private val forceRefresh = MutableLiveData(false)

    private var _data = forceRefresh.switchMap {
        Log.e("EA" , it.toString())
        repository.loadData().cachedIn(viewModelScope) }

    val data get() = _data

    fun refresh() {
        forceRefresh.value = true
    }


}