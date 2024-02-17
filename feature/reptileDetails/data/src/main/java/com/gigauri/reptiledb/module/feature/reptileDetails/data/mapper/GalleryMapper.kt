package com.gigauri.reptiledb.module.feature.reptileDetails.data.mapper

import com.gigauri.reptiledb.module.core.data.mapper.toDomain
import com.gigauri.reptiledb.module.core.domain.model.GalleryItem
import com.gigauri.reptiledb.module.feature.reptileDetails.data.remote.dto.GalleryDto

fun GalleryDto.toDomain(): GalleryItem {
    return GalleryItem(
        id,
        url,
        author.toDomain(),
        credits.orEmpty()
    )
}