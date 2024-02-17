package com.gigauri.reptiledb.module.core.data.remote.dto

import com.google.gson.annotations.SerializedName

data class FamilyDto(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String?,
)