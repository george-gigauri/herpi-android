package com.gigauri.reptiledb.module.feature.reptileDetails.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CoverageDto(
    @SerializedName("id") val id: String,
    @SerializedName("area") val area: String,
    @SerializedName("coordinates") val coordinates: List<CoordinateDto>
)