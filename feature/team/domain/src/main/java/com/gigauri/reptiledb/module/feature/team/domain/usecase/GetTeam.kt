package com.gigauri.reptiledb.module.feature.team.domain.usecase

import com.gigauri.reptiledb.module.core.domain.common.Resource
import com.gigauri.reptiledb.module.feature.team.domain.model.Team
import com.gigauri.reptiledb.module.feature.team.domain.repository.TeamRepository
import javax.inject.Inject

class GetTeam @Inject constructor(
    private val repository: TeamRepository
) {

    suspend fun execute(): Resource<List<Team>> {
        return repository.getTeam()
    }
}