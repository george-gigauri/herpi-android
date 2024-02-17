package com.gigauri.reptiledb.module.feature.team.data.remote.dto


import com.google.gson.annotations.SerializedName

data class SocialNetworkDto(
    @SerializedName("id")
    val id: Long,
    @SerializedName("network")
    val network: String,
    @SerializedName("networkLogoUrl")
    val networkLogoUrl: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("url")
    val url: String
)