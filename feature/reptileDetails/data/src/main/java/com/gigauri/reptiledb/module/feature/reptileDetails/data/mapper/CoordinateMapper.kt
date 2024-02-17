package com.gigauri.reptiledb.module.feature.reptileDetails.data.mapper

import com.gigauri.reptiledb.module.feature.reptileDetails.data.remote.dto.CoordinateDto
import com.gigauri.reptiledb.module.feature.reptileDetails.domain.model.Coordinate

fun CoordinateDto.toDomain(): Coordinate {
    return Coordinate(
        lat,
        lng
    )
}