package com.gigauri.reptiledb.module.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface AppDataStoreRepository {

    suspend fun setLanguage(code: String)
    fun getLanguage(): Flow<String?>

    suspend fun getOfflineDataLastUpdatedAt(): Long
    suspend fun setOfflineDataLastUpdatedAt(timestamp: Long)
}