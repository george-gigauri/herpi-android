package com.gigauri.reptiledb.module.feature.faq.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gigauri.reptiledb.module.feature.faq.data.local.model.FaqEntity

@Dao
interface FaqDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<FaqEntity>)

    @Query("DELETE FROM frequently_asked_questions")
    suspend fun deleteAll()

    @Query("SELECT * FROM frequently_asked_questions")
    suspend fun getAll(): List<FaqEntity>
}