package com.gigauri.reptiledb.module.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gigauri.reptiledb.module.core.data.local.dao.CategoryDao
import com.gigauri.reptiledb.module.core.data.local.dao.ReptileDao
import com.gigauri.reptiledb.module.core.data.local.dao.ReptileDetailsDao
import com.gigauri.reptiledb.module.core.data.local.db.AppDatabase.Companion.DATABASE_VERSION
import com.gigauri.reptiledb.module.core.data.local.model.CategoryEntity
import com.gigauri.reptiledb.module.core.data.local.model.CoordinateEntity
import com.gigauri.reptiledb.module.core.data.local.model.CoverageEntity
import com.gigauri.reptiledb.module.core.data.local.model.ReptileDetailsEntity
import com.gigauri.reptiledb.module.core.data.local.model.ReptileEntity
import com.gigauri.reptiledb.module.core.data.local.typeconverters.AuthorTypeConverters
import com.gigauri.reptiledb.module.core.data.local.typeconverters.FamilyTypeConverters
import com.gigauri.reptiledb.module.core.data.local.typeconverters.GalleryTypeConverters

@Database(
    entities = [
        ReptileEntity::class,
        CoverageEntity::class,
        CoordinateEntity::class,
        CategoryEntity::class,
        ReptileDetailsEntity::class
    ],
    version = DATABASE_VERSION,
    exportSchema = true,
    autoMigrations = []
)
@TypeConverters(
    value = [
        FamilyTypeConverters::class,
        AuthorTypeConverters::class,
        GalleryTypeConverters::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun reptileDao(): ReptileDao
    abstract fun categoryDao(): CategoryDao
    abstract fun reptileDetailsDao(): ReptileDetailsDao

    companion object {
        const val DATABASE_NAME = "herpi"
        const val DATABASE_VERSION = 1
    }
}