package com.gigauri.reptiledb.module.core.data.mapper

import com.gigauri.reptiledb.module.core.data.remote.dto.NearbyListDto
import com.gigauri.reptiledb.module.core.domain.model.NearbyList

fun NearbyListDto.toDomain(): NearbyList {
    return NearbyList(
        location = location,
        data = data.map { it.toDomain() }
    )
}