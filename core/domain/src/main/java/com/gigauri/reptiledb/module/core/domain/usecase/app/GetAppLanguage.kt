package com.gigauri.reptiledb.module.core.domain.usecase.app

import com.gigauri.reptiledb.module.core.domain.repository.AppDataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAppLanguage @Inject constructor(
    private val repository: AppDataStoreRepository
) {

    fun execute(): Flow<String?> = repository.getLanguage()
}