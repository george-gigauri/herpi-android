package com.gigauri.reptiledb.module.core.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = false)
    var id: String = "UNCATEGORIZED",
    var title: String = "",
    var titleTurned: String = "",
    var iconUrl: String? = null
)