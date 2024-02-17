package com.gigauri.reptiledb.module.feature.team.domain.model

data class Team(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val avatar: String?,
    val role: String,
    val socialNetworks: List<SocialNetwork>
)
