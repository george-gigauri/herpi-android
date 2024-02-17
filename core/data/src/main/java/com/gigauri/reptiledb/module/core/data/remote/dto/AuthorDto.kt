package com.gigauri.reptiledb.module.core.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AuthorDto(
    @SerializedName("id") val id: Long,
    @SerializedName("firstName") val firstName: String,
    @SerializedName("lastName") val lastName: String,
    @SerializedName("avatar") val avatar: String?,
)