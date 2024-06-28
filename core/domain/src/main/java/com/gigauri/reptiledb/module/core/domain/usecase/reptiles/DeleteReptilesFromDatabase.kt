package com.gigauri.reptiledb.module.core.domain.usecase.reptiles

import com.gigauri.reptiledb.module.core.domain.repository.ReptileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteReptilesFromDatabase @Inject constructor(
    private val repository: ReptileRepository
) {

    suspend fun execute() = withContext(Dispatchers.IO) {
        repository.deleteAllFromDatabase()
    }
}