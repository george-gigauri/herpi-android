package com.gigauri.reptiledb.module.feature.team.data.repository

import com.gigauri.reptiledb.module.core.data.common.safeApiCall
import com.gigauri.reptiledb.module.core.domain.common.Resource
import com.gigauri.reptiledb.module.feature.team.data.mapper.toDomain
import com.gigauri.reptiledb.module.feature.team.data.remote.api.TeamApi
import com.gigauri.reptiledb.module.feature.team.domain.model.Team
import com.gigauri.reptiledb.module.feature.team.domain.model.TeamGroupItem
import com.gigauri.reptiledb.module.feature.team.domain.repository.TeamRepository
import javax.inject.Inject

class TeamRepositoryImpl @Inject constructor(
    private val api: TeamApi
) : TeamRepository {

    override suspend fun getTeam(): Resource<List<TeamGroupItem>> {
        return safeApiCall { api.getTeam().map { it.toDomain() } }
    }
}