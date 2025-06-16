package com.gigauri.reptiledb.module.feature.team.data.remote.api

import com.gigauri.reptiledb.module.feature.team.data.remote.dto.TeamDto
import com.gigauri.reptiledb.module.feature.team.data.remote.dto.TeamGroupItemDto
import com.gigauri.reptiledb.module.feature.team.domain.model.TeamGroupItem
import retrofit2.http.GET

interface TeamApi {

    @GET("api/v2/team")
    suspend fun getTeam(): List<TeamGroupItemDto>
}