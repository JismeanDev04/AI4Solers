package com.example.ai4solers.core.common

sealed class Resource<T>(val data: T? = null, val message: String? = null) {

    //Success phase
    class Success<T>(data: T) : Resource<T>(data)

    //Error phase
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)

    //Loading phase
    class Loading<T>(data: T? =null): Resource<T>(data)
}