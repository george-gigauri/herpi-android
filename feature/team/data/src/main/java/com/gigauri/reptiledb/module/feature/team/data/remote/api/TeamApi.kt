package com.gigauri.reptiledb.module.feature.team.data.remote.api

import com.gigauri.reptiledb.module.feature.team.data.remote.dto.TeamDto
import retrofit2.http.GET

interface TeamApi {

    @GET("api/v1/team")
    suspend fun getTeam(): List<TeamDto>
}