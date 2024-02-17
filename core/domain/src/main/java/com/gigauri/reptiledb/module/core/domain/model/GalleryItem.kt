package com.gigauri.reptiledb.module.core.domain.model

data class GalleryItem(
    val id: Long,
    val url: String?,
    val author: Author?,
    val credits: List<String>
)