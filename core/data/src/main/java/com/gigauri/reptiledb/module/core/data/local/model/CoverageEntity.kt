package com.gigauri.reptiledb.module.core.data.local.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "coverage")
data class CoverageEntity(
    @PrimaryKey
    var id: String = "",
    var reptile: Int = -1,
    var area: String = "",
    @Ignore
    var coordinates: List<CoordinateEntity> = emptyList()
)