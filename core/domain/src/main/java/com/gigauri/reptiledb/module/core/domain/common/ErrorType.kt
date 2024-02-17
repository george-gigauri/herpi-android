package com.gigauri.reptiledb.module.core.domain.common

import java.io.IOException

sealed class ErrorType {
    data class Network(val ex: IOException) : ErrorType()
    data class Http(val statusCode: Int, val message: String) : ErrorType()
    data class Generic(val exception: Exception) : ErrorType()

}