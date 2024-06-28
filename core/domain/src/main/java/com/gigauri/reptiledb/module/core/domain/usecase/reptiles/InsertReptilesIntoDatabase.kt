package com.gigauri.reptiledb.module.core.domain.usecase.reptiles

import com.gigauri.reptiledb.module.core.domain.model.Reptile
import com.gigauri.reptiledb.module.core.domain.repository.ReptileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InsertReptilesIntoDatabase @Inject constructor(
    private val repository: ReptileRepository
) {

    suspend fun execute(list: List<Reptile>) = withContext(Dispatchers.IO) {
        repository.insertIntoDatabase(list)
    }
}