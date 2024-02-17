package com.gigauri.reptiledb.module.feature.reptileDetails.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CoordinateDto(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lng") val lng: Double
)
