package com.gigauri.reptiledb.module.feature.reptileDetails.data.remote.api

import com.gigauri.reptiledb.module.feature.reptileDetails.data.remote.dto.CoverageDto
import com.gigauri.reptiledb.module.feature.reptileDetails.data.remote.dto.ReptileDetailsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ReptileDetailsApi {

    @GET("api/v1/reptiles/{id}")
    suspend fun getReptileDetails(
        @Path("id") id: Long
    ): ReptileDetailsDto

    @GET("api/v1/reptiles/{id}/coverage")
    suspend fun getReptileDistribution(
        @Path("id") id: Long
    ): List<CoverageDto>
}