package com.gigauri.reptiledb.module.feature.faq.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gigauri.reptiledb.module.feature.faq.data.local.dao.FaqDao
import com.gigauri.reptiledb.module.feature.faq.data.local.model.FaqEntity

@Database(
    entities = [
        FaqEntity::class
    ],
    version = 1,
    exportSchema = true,
    autoMigrations = []
)
@TypeConverters(
    value = []
)
abstract class FaqDatabase : RoomDatabase() {

    abstract fun dao(): FaqDao

    companion object {
        const val DATABASE_NAME = "herpi_faq"
    }
}