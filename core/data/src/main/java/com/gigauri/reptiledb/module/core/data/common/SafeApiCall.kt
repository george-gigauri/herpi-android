package com.gigauri.reptiledb.module.core.data.common

import com.gigauri.reptiledb.module.core.domain.common.ErrorType
import com.gigauri.reptiledb.module.core.domain.common.Resource
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(call: suspend () -> T): Resource<T> {
    return try {
        Resource.Success(call())
    } catch (http: HttpException) {
        Resource.Error(ErrorType.Http(http.code(), http.message()))
    } catch (network: IOException) {
        Resource.Error(ErrorType.Network(network))
    } catch (unknown: Exception) {
        Resource.Error(ErrorType.Generic(unknown))
    }
}