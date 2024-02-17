package com.gigauri.reptiledb.module.feature.faq.data.remote.api

import com.gigauri.reptiledb.module.feature.faq.data.remote.dto.FaqDto
import retrofit2.http.GET

interface FaqApi {

    @GET("api/v1/faq")
    suspend fun getFaq(): List<FaqDto>
}