package com.gigauri.reptiledb.module.feature.team.data.mapper

import com.gigauri.reptiledb.module.feature.team.data.remote.dto.SocialNetworkDto
import com.gigauri.reptiledb.module.feature.team.domain.model.SocialNetwork

fun SocialNetworkDto.toDomain(): SocialNetwork {
    return SocialNetwork(
        id,
        network,
        networkLogoUrl,
        username,
        url
    )
}