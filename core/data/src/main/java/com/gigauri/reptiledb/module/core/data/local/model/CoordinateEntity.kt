package com.gigauri.reptiledb.module.core.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "coordinates")
data class CoordinateEntity(
    @PrimaryKey
    var id: String = "",
    var coverage: String = "",
    var lat: Double = 0.0,
    var lng: Double = 0.0
)