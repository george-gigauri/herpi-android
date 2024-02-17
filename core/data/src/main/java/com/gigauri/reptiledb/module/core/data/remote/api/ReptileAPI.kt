package com.gigauri.reptiledb.module.core.data.remote.api

import com.gigauri.reptiledb.module.core.data.remote.dto.ReptileDto
import retrofit2.http.*

interface ReptileAPI {

    @GET("api/v1/reptiles")
    suspend fun getReptiles(
        @Query("page") page: Int = 0,
        @Query("pageSize") perPage: Int = 100,
        @Query("type") type: String? = null,
        @Query("venomous") isVenomous: Boolean? = null,
        @Query("endemic") isEndemic: Boolean? = null
    ): List<ReptileDto>

    @GET("api/v1/reptiles/nearby")
    suspend fun getNearby(
        @Query("lat") lat: Double,
        @Query("lng") lng: Double,
        @Query("type") type: String? = null,
    ): List<ReptileDto>
}