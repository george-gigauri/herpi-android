package com.gigauri.reptiledb.module.feature.team.data.mapper

import com.gigauri.reptiledb.module.feature.team.data.remote.dto.TeamDto
import com.gigauri.reptiledb.module.feature.team.domain.model.Team

fun TeamDto.toDomain(): Team {
    return Team(
        id,
        firstName,
        lastName,
        email,
        avatar,
        role,
        socialNetworks.map { it.toDomain() }
    )
}