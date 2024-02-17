package com.gigauri.reptiledb.module.feature.team.domain.model

data class SocialNetwork(
    val id: Long,
    val networkName: String,
    val networkLogo: String?,
    val username: String,
    val profileUrl: String
)
