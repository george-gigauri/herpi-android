package com.gigauri.reptiledb.module.core.domain.model

data class NearbyList(
    val location: String,
    val data: List<Reptile>
)