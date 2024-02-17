package com.gigauri.reptiledb.module.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gigauri.reptiledb.module.core.data.local.model.ReptileDetailsEntity

@Dao
interface ReptileDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<ReptileDetailsEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ReptileDetailsEntity)

    @Query("DELETE FROM reptile_details WHERE id=:id")
    suspend fun deleteById(id: Long)

    @Query(
        """
        SELECT * FROM reptile_details 
        WHERE :id = id
    """
    )
    suspend fun getById(id: Long): ReptileDetailsEntity?
}