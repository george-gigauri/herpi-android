package com.gigauri.reptiledb.module.feature.team.domain.repository

import com.gigauri.reptiledb.module.core.domain.common.Resource
import com.gigauri.reptiledb.module.feature.team.domain.model.Team
import com.gigauri.reptiledb.module.feature.team.domain.model.TeamGroupItem

interface TeamRepository {
    suspend fun getTeam(): Resource<List<TeamGroupItem>>
}