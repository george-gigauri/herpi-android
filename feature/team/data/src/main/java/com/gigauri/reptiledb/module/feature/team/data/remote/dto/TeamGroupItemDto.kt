package com.gigauri.reptiledb.module.feature.team.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TeamGroupItemDto(
    @SerializedName("category") val category: String,
    @SerializedName("data") val data: List<TeamDto>
)
