package com.gigauri.reptiledb.module.feature.reptileDetails.domain.usecase

import com.gigauri.reptiledb.module.core.domain.common.Resource
import com.gigauri.reptiledb.module.feature.reptileDetails.domain.model.Distribution
import com.gigauri.reptiledb.module.feature.reptileDetails.domain.repository.ReptileDetailsRepository
import javax.inject.Inject

class GetDistribution @Inject constructor(
    private val repository: ReptileDetailsRepository
) {

    suspend fun execute(reptileId: Long): Resource<List<Distribution>> {
        return repository.getReptileDistribution(reptileId)
    }
}