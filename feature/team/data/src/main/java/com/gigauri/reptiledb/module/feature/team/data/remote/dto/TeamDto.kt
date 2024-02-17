package com.gigauri.reptiledb.module.feature.team.data.remote.dto


import com.google.gson.annotations.SerializedName

data class TeamDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("socialNetworks")
    val socialNetworks: List<SocialNetworkDto>,
    @SerializedName("role")
    val role: String
)