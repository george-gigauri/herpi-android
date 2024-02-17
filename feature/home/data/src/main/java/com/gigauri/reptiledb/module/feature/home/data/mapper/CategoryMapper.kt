package com.gigauri.reptiledb.module.feature.home.data.mapper

import com.gigauri.reptiledb.module.core.data.local.model.CategoryEntity
import com.gigauri.reptiledb.module.feature.home.data.remote.dto.CategoryDto
import com.gigauri.reptiledb.module.feature.home.domain.model.Category


fun CategoryDto.toDomain(): Category {
    return Category(
        id, title, titleTurned, iconUrl
    )
}

fun CategoryEntity.toDomain(): Category {
    return Category(
        id, title, titleTurned, iconUrl
    )
}

fun Category.toEntity(): CategoryEntity {
    return CategoryEntity(id, title, titleTurned, iconUrl)
}