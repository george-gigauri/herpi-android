package com.gigauri.reptiledb.module.core.domain.usecase.reptiles

import com.gigauri.reptiledb.module.core.domain.common.Resource
import com.gigauri.reptiledb.module.core.domain.model.NearbyList
import com.gigauri.reptiledb.module.core.domain.model.Reptile
import com.gigauri.reptiledb.module.core.domain.repository.ReptileRepository
import javax.inject.Inject

class GetNearbyReptiles @Inject constructor(
    private val repository: ReptileRepository
) {

    suspend fun execute(
        lat: Double,
        lng: Double,
        type: String? = null
    ): Resource<NearbyList> {
        return repository.getNearby(
            lat,
            lng,
            if (type == "UNCATEGORIZED") null else type
        )
    }
}