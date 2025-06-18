package com.gigauri.reptiledb.module.feature.team.data.mapper

import com.gigauri.reptiledb.module.feature.team.data.remote.dto.TeamGroupItemDto
import com.gigauri.reptiledb.module.feature.team.domain.model.TeamGroupItem

fun TeamGroupItemDto.toDomain(): TeamGroupItem = TeamGroupItem(
    category = category,
    data = data.map { it.toDomain() }
)