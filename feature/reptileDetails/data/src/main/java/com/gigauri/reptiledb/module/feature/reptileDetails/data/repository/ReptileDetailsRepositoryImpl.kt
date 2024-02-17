package com.gigauri.reptiledb.module.feature.reptileDetails.data.repository

import com.gigauri.reptiledb.module.core.data.common.safeApiCall
import com.gigauri.reptiledb.module.core.data.local.dao.ReptileDetailsDao
import com.gigauri.reptiledb.module.core.domain.common.Resource
import com.gigauri.reptiledb.module.feature.reptileDetails.data.mapper.toDomain
import com.gigauri.reptiledb.module.feature.reptileDetails.data.mapper.toEntity
import com.gigauri.reptiledb.module.feature.reptileDetails.data.remote.api.ReptileDetailsApi
import com.gigauri.reptiledb.module.feature.reptileDetails.domain.model.Distribution
import com.gigauri.reptiledb.module.feature.reptileDetails.domain.model.ReptileFull
import com.gigauri.reptiledb.module.feature.reptileDetails.domain.repository.ReptileDetailsRepository
import javax.inject.Inject

class ReptileDetailsRepositoryImpl @Inject constructor(
    private val api: ReptileDetailsApi,
    private val db: ReptileDetailsDao
) : ReptileDetailsRepository {

    override suspend fun getReptileDetails(id: Long): Resource<ReptileFull> {
        return safeApiCall { api.getReptileDetails(id).toDomain() }
    }

    override suspend fun getReptileDistribution(id: Long): Resource<List<Distribution>> {
        return safeApiCall { api.getReptileDistribution(id).map { it.toDomain() } }
    }

    override suspend fun deleteByIdFromDatabase(id: Long) {
        db.deleteById(id)
    }

    override suspend fun insertToDatabase(item: ReptileFull) {
        db.insert(item.toEntity())
    }

    override suspend fun getByIdFromDatabase(id: Long): ReptileFull? {
        return db.getById(id)?.toDomain()
    }
}