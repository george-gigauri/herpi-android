package com.gigauri.reptiledb.module.core.domain.common

sealed class Resource<T> {
    data class Loading<T>(val data: T? = null) : Resource<T>()
    data class Success<T>(val data: @JvmSuppressWildcards T) : Resource<T>()
    data class Error<T>(
        val errorType: ErrorType,
        val data: T? = null
    ) : Resource<T>()
}
