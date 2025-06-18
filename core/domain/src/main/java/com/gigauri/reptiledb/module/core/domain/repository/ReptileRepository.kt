package com.gigauri.reptiledb.module.core.domain.repository

import com.gigauri.reptiledb.module.core.domain.common.Resource
import com.gigauri.reptiledb.module.core.domain.model.NearbyList
import com.gigauri.reptiledb.module.core.domain.model.Reptile

interface ReptileRepository {
    suspend fun getNearby(
        lat: Double,
        lng: Double,
        type: String?
    ): Resource<NearbyList>

    suspend fun getAllReptiles(
        page: Int = 0,
        perPage: Int = 30,
        type: String? = null,
        isVenomous: Boolean? = null,
        isEndemic: Boolean? = null
    ): Resource<List<Reptile>>

    suspend fun deleteAllFromDatabase()

    suspend fun isDatabaseNotEmpty(): Boolean

    suspend fun fetchAllFromDatabase(
        type: String? = null,
        isVenomous: Boolean? = null,
        isEndemic: Boolean? = null
    ): Resource<List<Reptile>>

    suspend fun insertIntoDatabase(
        list: List<Reptile>
    )
}