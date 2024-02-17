package com.gigauri.reptiledb.module.feature.reptileDetails.data.mapper

import com.gigauri.reptiledb.module.core.data.mapper.toDomain
import com.gigauri.reptiledb.module.core.domain.model.Author
import com.gigauri.reptiledb.module.core.domain.model.Family
import com.gigauri.reptiledb.module.feature.reptileDetails.data.remote.dto.ReptileDetailsDto
import com.gigauri.reptiledb.module.feature.reptileDetails.domain.model.ReptileFull

fun ReptileDetailsDto.toDomain(): ReptileFull {
    return ReptileFull(
        id,
        name,
        scientificName,
        description,
        image,
        transparentThumbnail,
        family.toDomain(),
        addedBy.toDomain(),
        venomous,
        hasMildVenom,
        hasRedFlag,
        gallery.map { it.toDomain() }
    )
}

fun ReptileFull.toEntity(): com.gigauri.reptiledb.module.core.data.local.model.ReptileDetailsEntity {
    return com.gigauri.reptiledb.module.core.data.local.model.ReptileDetailsEntity(
        id, name, scientificName, description,
        scientificName.split(" ").firstOrNull() ?: "---",
        thumbnailUrl,
        transparentThumbnailUrl,
        gallery,
        author,
        family,
        isEndangered,
        isVenomous,
        hasMildVenom,
        "",
    )
}

fun com.gigauri.reptiledb.module.core.data.local.model.ReptileDetailsEntity.toDomain(): ReptileFull {
    return ReptileFull(
        id,
        name,
        scientificName,
        description,
        image,
        transparentImageUrl,
        family ?: Family(-1, "---"),
        addedBy ?: Author(-1, "---", null),
        venomous,
        hasMildVenom,
        hasRedFlag,
        gallery
    )
}