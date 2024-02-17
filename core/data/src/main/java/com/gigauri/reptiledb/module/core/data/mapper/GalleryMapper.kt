package com.gigauri.reptiledb.module.core.data.mapper

import com.gigauri.reptiledb.module.core.data.remote.dto.GalleryDto
import com.gigauri.reptiledb.module.core.domain.model.GalleryItem

fun GalleryDto.toDomain(): GalleryItem {
    return GalleryItem(
        id,
        url,
        author.toDomain(),
        credits.orEmpty()
    )
}