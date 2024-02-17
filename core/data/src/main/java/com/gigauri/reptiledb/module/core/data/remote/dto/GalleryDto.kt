package com.gigauri.reptiledb.module.core.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GalleryDto(
    @SerializedName("author") val author: AuthorDto,
    @SerializedName("credits") val credits: List<String>?,
    @SerializedName("id") val id: Long,
    @SerializedName("url") val url: String
)