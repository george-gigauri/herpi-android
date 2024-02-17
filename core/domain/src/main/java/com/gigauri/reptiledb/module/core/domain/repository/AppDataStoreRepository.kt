package com.gigauri.reptiledb.module.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface AppDataStoreRepository {

    suspend fun setLanguage(code: String)
    fun getLanguage(): Flow<String?>
}