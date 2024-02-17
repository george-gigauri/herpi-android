package com.gigauri.reptiledb.module.core.domain.usecase.app

import com.gigauri.reptiledb.module.core.domain.repository.AppDataStoreRepository
import javax.inject.Inject

class SetLanguage @Inject constructor(
    private val repository: AppDataStoreRepository
) {

    suspend fun execute(code: String) {
        repository.setLanguage(code)
    }
}