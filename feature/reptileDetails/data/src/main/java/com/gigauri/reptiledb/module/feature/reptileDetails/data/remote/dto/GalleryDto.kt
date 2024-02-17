package com.gigauri.reptiledb.module.feature.reptileDetails.data.remote.dto

import com.gigauri.reptiledb.module.core.data.remote.dto.AuthorDto
import com.google.gson.annotations.SerializedName

data class GalleryDto(
    @SerializedName("author") val author: AuthorDto,
    @SerializedName("credits") val credits: List<String>?,
    @SerializedName("id") val id: Long,
    @SerializedName("url") val url: String
)