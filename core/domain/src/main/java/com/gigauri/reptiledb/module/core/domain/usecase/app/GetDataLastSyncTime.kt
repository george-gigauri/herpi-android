package com.gigauri.reptiledb.module.core.domain.usecase.app

import com.gigauri.reptiledb.module.core.domain.repository.AppDataStoreRepository
import javax.inject.Inject

class GetDataLastSyncTime @Inject constructor(
    private val repository: AppDataStoreRepository
) {

    suspend fun execute(): Long = repository.getOfflineDataLastUpdatedAt()
}