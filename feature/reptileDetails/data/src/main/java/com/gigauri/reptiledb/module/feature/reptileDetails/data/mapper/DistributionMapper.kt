package com.gigauri.reptiledb.module.feature.reptileDetails.data.mapper

import com.gigauri.reptiledb.module.feature.reptileDetails.data.remote.dto.CoverageDto
import com.gigauri.reptiledb.module.feature.reptileDetails.domain.model.Distribution

fun CoverageDto.toDomain(): Distribution {
    return Distribution(
        id,
        area,
        coordinates.map { it.toDomain() }
    )
}