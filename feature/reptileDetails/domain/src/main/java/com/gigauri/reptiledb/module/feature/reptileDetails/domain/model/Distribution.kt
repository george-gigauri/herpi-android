package com.gigauri.reptiledb.module.feature.reptileDetails.domain.model

data class Distribution(
    val id: String,
    val area: String,
    val coordinates: List<Coordinate>
)
