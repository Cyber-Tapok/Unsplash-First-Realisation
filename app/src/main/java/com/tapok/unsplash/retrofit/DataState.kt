package com.tapok.unsplash.retrofit



sealed class DataState<out T> {
    object Idle : DataState<Nothing>()
    object Start : DataState<Nothing>()
    data class Success<T>(val data: T) :DataState<T>()
    data class Failed(val e: Exception) : DataState<Nothing>()
}
