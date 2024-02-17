package com.gigauri.reptiledb.module.core.domain.usecase.reptiles

import com.gigauri.reptiledb.module.core.domain.common.Resource
import com.gigauri.reptiledb.module.core.domain.model.Reptile
import com.gigauri.reptiledb.module.core.domain.repository.ReptileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllReptiles @Inject constructor(
    private val repository: ReptileRepository
) {

    suspend fun execute(
        page: Int,
        pageSize: Int,
        reptileType: String? = null,
        isVenomous: Boolean? = null,
        isEndangered: Boolean? = null
    ): Resource<List<Reptile>> = withContext(Dispatchers.IO) {
        return@withContext repository.getAllReptiles(
            page,
            pageSize,
            if (reptileType == "UNCATEGORIZED") null else reptileType,
            isVenomous,
            isEndangered
        ).let {
            if (it is Resource.Success) {
                repository.deleteAllFromDatabase()
                repository.insertIntoDatabase(it.data)
            }
            repository.fetchAllFromDatabase(
                reptileType,
                isVenomous,
                isEndangered
            )
        }
    }
}