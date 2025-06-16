package com.gigauri.reptiledb.module.feature.team.domain.usecase

import com.gigauri.reptiledb.module.core.domain.common.Resource
import com.gigauri.reptiledb.module.feature.team.domain.model.Team
import com.gigauri.reptiledb.module.feature.team.domain.model.TeamGroupItem
import com.gigauri.reptiledb.module.feature.team.domain.repository.TeamRepository
import javax.inject.Inject

class GetTeam @Inject constructor(
    private val repository: TeamRepository
) {

    suspend fun execute(): Resource<List<TeamGroupItem>> {
        return repository.getTeam()
    }
}