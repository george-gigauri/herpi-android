package com.gigauri.reptiledb.module.core.data.mapper

import com.gigauri.reptiledb.module.core.data.local.model.ReptileEntity
import com.gigauri.reptiledb.module.core.data.remote.dto.ReptileDto
import com.gigauri.reptiledb.module.core.domain.model.Reptile

fun ReptileDto.toDomain(): Reptile {
    return Reptile(
        id,
        addedBy?.toDomain(),
        family?.toDomain(),
        image, transparentThumbnail, name, scientificName, type, venomous, hasMildVenom, hasRedFlag
    )
}

fun ReptileEntity.toDomain(): Reptile {
    return Reptile(
        id.toLong(),
        addedBy,
        family,
        image,
        transparentThumbnail,
        name,
        scientificName,
        type,
        isVenomous,
        hasMildVenom,
        hasRedFlag,
    )
}

fun Reptile.toEntity(): ReptileEntity {
    return ReptileEntity(
        id.toInt(), name, scientificName, family,
        type = type,
        hasRedFlag = hasRedFlag,
        hasMildVenom = hasMildVenom,
        isVenomous = venomous,
        image = image,
        transparentThumbnail = transparentImage,
        addedBy = addedBy,
        gallery = emptyList(),
    )
}