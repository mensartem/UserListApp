package com.example.userlistapp.util

sealed class Resource<out T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error(val exception: Throwable) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}

sealed class Status {

    object Success : Status()

    data class Error(val exception: Throwable) : Status()

    object Loading : Status()
}
