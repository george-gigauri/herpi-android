package com.gigauri.reptiledb.module.feature.faq.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "frequently_asked_questions")
data class FaqEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Long = 0,
    var question: String = "",
    var answer: String = "",
)