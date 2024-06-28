package com.gigauri.reptiledb.module.core.domain.usecase.app

import com.gigauri.reptiledb.module.core.domain.repository.AppDataStoreRepository
import javax.inject.Inject

class SetDataLastSyncAt @Inject constructor(
    private val repository: AppDataStoreRepository
) {

    suspend fun execute(time: Long) = repository.setOfflineDataLastUpdatedAt(time)
}