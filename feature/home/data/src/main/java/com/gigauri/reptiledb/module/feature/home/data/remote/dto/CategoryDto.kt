package com.gigauri.reptiledb.module.feature.home.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("titleTurned") val titleTurned: String,
    @SerializedName("iconUrl") val iconUrl: String?
)