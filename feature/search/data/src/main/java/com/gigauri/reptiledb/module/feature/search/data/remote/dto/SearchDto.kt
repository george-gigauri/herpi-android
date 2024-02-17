package com.gigauri.reptiledb.module.feature.search.data.remote.dto

import com.gigauri.reptiledb.module.core.data.remote.dto.ReptileDto
import com.google.gson.annotations.SerializedName

data class SearchDto(
    @SerializedName("data") val data: List<ReptileDto>,
    @SerializedName("page") val page: Int,
    @SerializedName("pageSize") val pageSize: Int,
    @SerializedName("totalPages") val totalPages: Int,
)