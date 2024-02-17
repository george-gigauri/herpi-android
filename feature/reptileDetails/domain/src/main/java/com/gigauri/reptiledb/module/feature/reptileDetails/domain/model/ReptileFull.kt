package com.gigauri.reptiledb.module.feature.reptileDetails.domain.model

import com.gigauri.reptiledb.module.core.domain.model.Author
import com.gigauri.reptiledb.module.core.domain.model.Family
import com.gigauri.reptiledb.module.core.domain.model.GalleryItem

data class ReptileFull(
    val id: Long,
    val name: String,
    val scientificName: String,
    val description: String,
    val thumbnailUrl: String?,
    val transparentThumbnailUrl: String?,
    val family: Family,
    val author: Author,
    val isVenomous: Boolean,
    val hasMildVenom: Boolean,
    val isEndangered: Boolean,
    val gallery: List<GalleryItem>
)