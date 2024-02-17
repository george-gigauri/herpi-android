package com.gigauri.reptiledb.module.core.data.mapper

import com.gigauri.reptiledb.module.core.data.remote.dto.FamilyDto
import com.gigauri.reptiledb.module.core.domain.model.Family

fun FamilyDto.toDomain(): Family {
    return Family(
        id, name
    )
}