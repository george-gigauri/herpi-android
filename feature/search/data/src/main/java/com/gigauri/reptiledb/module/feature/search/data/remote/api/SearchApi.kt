package com.gigauri.reptiledb.module.feature.search.data.remote.api

import com.gigauri.reptiledb.module.feature.search.data.remote.dto.SearchDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("api/v1/reptiles/search")
    suspend fun search(
        @Query("query") keyword: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
    ): SearchDto
}