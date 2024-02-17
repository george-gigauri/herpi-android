package com.gigauri.reptiledb.module.feature.reptileDetails.domain.repository

import com.gigauri.reptiledb.module.core.domain.common.Resource
import com.gigauri.reptiledb.module.feature.reptileDetails.domain.model.Distribution
import com.gigauri.reptiledb.module.feature.reptileDetails.domain.model.ReptileFull

interface ReptileDetailsRepository {

    suspend fun getReptileDetails(id: Long): Resource<ReptileFull>

    suspend fun getReptileDistribution(id: Long): Resource<List<Distribution>>

    suspend fun deleteByIdFromDatabase(id: Long)

    suspend fun insertToDatabase(item: ReptileFull)

    suspend fun getByIdFromDatabase(id: Long): ReptileFull?
}