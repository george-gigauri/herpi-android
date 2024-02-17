package com.gigauri.reptiledb.module.core.data.repository

import com.gigauri.reptiledb.module.core.data.common.safeApiCall
import com.gigauri.reptiledb.module.core.data.local.dao.ReptileDao
import com.gigauri.reptiledb.module.core.data.mapper.toDomain
import com.gigauri.reptiledb.module.core.data.mapper.toEntity
import com.gigauri.reptiledb.module.core.data.remote.api.ReptileAPI
import com.gigauri.reptiledb.module.core.domain.common.Resource
import com.gigauri.reptiledb.module.core.domain.model.Reptile
import com.gigauri.reptiledb.module.core.domain.repository.ReptileRepository
import javax.inject.Inject

class ReptileRepositoryImpl @Inject constructor(
    private val api: ReptileAPI,
    private val reptileDao: ReptileDao
) : ReptileRepository {

    override suspend fun getNearby(
        lat: Double,
        lng: Double,
        type: String?
    ): Resource<List<Reptile>> {
        return safeApiCall { api.getNearby(lat, lng, type = type).map { it.toDomain() } }
    }

    override suspend fun getAllReptiles(
        page: Int,
        perPage: Int,
        type: String?,
        isVenomous: Boolean?,
        isEndemic: Boolean?
    ): Resource<List<Reptile>> {
        return safeApiCall {
            api.getReptiles(
                page,
                perPage,
                type,
                isVenomous,
                isEndemic
            ).sortedBy {
                it.venomous
            }.map { it.toDomain() }
        }
    }

    override suspend fun deleteAllFromDatabase() {
        reptileDao.deleteAll()
    }

    override suspend fun isDatabaseNotEmpty(): Boolean {
        return reptileDao.isNotEmpty()
    }

    override suspend fun fetchAllFromDatabase(
        type: String?,
        isVenomous: Boolean?,
        isEndemic: Boolean?
    ): Resource<List<Reptile>> {
        return Resource.Success(
            reptileDao.getAllReptiles(
                type,
                isVenomous,
                isEndemic
            ).map { it.toDomain() }
        )
    }

    override suspend fun insertIntoDatabase(list: List<Reptile>) {
        reptileDao.insert(list.map { it.toEntity() })
    }
}