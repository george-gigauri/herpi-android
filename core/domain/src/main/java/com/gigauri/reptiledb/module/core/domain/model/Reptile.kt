package com.gigauri.reptiledb.module.core.domain.model

data class Reptile(
    val id: Long,
    val addedBy: Author?,
    val family: Family?,
    val image: String?,
    val transparentImage: String?,
    val name: String,
    val scientificName: String,
    val type: String,
    val venomous: Boolean,
    val hasMildVenom: Boolean,
    val hasRedFlag: Boolean,
)