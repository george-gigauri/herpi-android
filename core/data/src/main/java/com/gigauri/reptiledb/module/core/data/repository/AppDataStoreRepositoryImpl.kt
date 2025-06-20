package com.gigauri.reptiledb.module.core.data.repository

import com.gigauri.reptiledb.module.core.data.local.preferences.PreferencesDataStore
import com.gigauri.reptiledb.module.core.domain.repository.AppDataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AppDataStoreRepositoryImpl @Inject constructor(
    private val dataStore: PreferencesDataStore
) : AppDataStoreRepository {

    override suspend fun setLanguage(code: String) {
        dataStore.setLanguage(code)
    }

    override fun getLanguage(): Flow<String?> {
        return dataStore.getLanguage()
    }

    override suspend fun getOfflineDataLastUpdatedAt(): Long {
        return dataStore.getOfflineDataLastUpdateTimestamp().first()
    }

    override suspend fun setOfflineDataLastUpdatedAt(timestamp: Long) {
        dataStore.setOfflineDataUpdateTimestamp(timestamp)
    }
}