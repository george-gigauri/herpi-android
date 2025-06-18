package com.gigauri.reptiledb.module.core.data.remote.dto

import com.google.gson.annotations.SerializedName

data class NearbyListDto(
    @SerializedName("location") val location: String,
    @SerializedName("data") val data: List<ReptileDto>,
)