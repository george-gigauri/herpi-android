package com.gigauri.reptiledb.module.feature.team.domain.repository

import com.gigauri.reptiledb.module.core.domain.common.Resource
import com.gigauri.reptiledb.module.feature.team.domain.model.Team

interface TeamRepository {
    suspend fun getTeam(): Resource<List<Team>>
}