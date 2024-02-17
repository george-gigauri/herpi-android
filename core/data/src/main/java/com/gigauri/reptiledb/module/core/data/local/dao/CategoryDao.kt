package com.gigauri.reptiledb.module.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gigauri.reptiledb.module.core.data.local.model.CategoryEntity

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<CategoryEntity>)

    @Query("SELECT * FROM category")
    suspend fun getCategories(): List<CategoryEntity>

    @Query("SELECT COUNT(*) > 0 FROM category")
    suspend fun isEmpty(): Boolean

    @Query("DELETE FROM category")
    suspend fun deleteAll()
}