package com.gigauri.reptiledb.module.core.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gigauri.reptiledb.module.core.data.local.model.CoordinateEntity
import com.gigauri.reptiledb.module.core.data.local.model.CoverageEntity
import com.gigauri.reptiledb.module.core.data.local.model.ReptileEntity

@Dao
interface ReptileDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ReptileEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<ReptileEntity>)

    @Query("DELETE FROM REPTILE_GENERAL WHERE id=:id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM REPTILE_GENERAL")
    suspend fun deleteAll()

    @Query("SELECT EXISTS (SELECT * FROM REPTILE_GENERAL WHERE id=:id)")
    suspend fun existsById(id: Int): Boolean

    @Query("SELECT * FROM REPTILE_GENERAL WHERE id=:id")
    suspend fun getById(id: Int): ReptileEntity

    @Query("SELECT COUNT(*) > 0 FROM REPTILE_GENERAL")
    suspend fun isNotEmpty(): Boolean

    @Query("SELECT * FROM REPTILE_GENERAL ORDER BY isVenomous")
    fun getAll(): PagingSource<Int, ReptileEntity>

    @Query(
        """
        SELECT * FROM REPTILE_GENERAL 
        WHERE (:type IS NULL OR (:type="UNCATEGORIZED" OR (:type=type)))
        AND (:isVenomous IS NULL OR ((:isVenomous=isVenomous)OR (:isVenomous=hasMildVenom)))
        AND (:isEndemic IS NULL OR :isEndemic=hasRedFlag)
        ORDER BY isVenomous
        """
    )
    fun getAllReptiles(
        type: String?,
        isVenomous: Boolean?,
        isEndemic: Boolean?
    ): List<ReptileEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: CoverageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entity: List<CoverageEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(coordinate: CoordinateEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoordinates(coordinates: List<CoordinateEntity>)

    @Query("SELECT * FROM COORDINATES WHERE coverage=:coverage")
    suspend fun getCoordinates(coverage: String): List<CoordinateEntity>

    @Query("DELETE FROM COORDINATES")
    suspend fun deleteCoordinates()

    @Query("DELETE FROM COORDINATES WHERE id=:id")
    suspend fun deleteCoordinate(id: Int)

    @Query("DELETE FROM COVERAGE")
    suspend fun deleteAllCoverage()

    @Query("SELECT * FROM coverage WHERE reptile=:id")
    suspend fun getCoverageByReptileId(id: Int): List<CoverageEntity>
}