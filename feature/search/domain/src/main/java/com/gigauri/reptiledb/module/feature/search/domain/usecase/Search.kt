package com.gigauri.reptiledb.module.feature.search.domain.usecase

import androidx.paging.PagingData
import com.gigauri.reptiledb.module.core.domain.model.Reptile
import com.gigauri.reptiledb.module.feature.search.domain.repository.SearchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class Search @Inject constructor(
    private val repository: SearchRepository
) {

    suspend fun execute(keyword: String): Flow<PagingData<Reptile>> {
        return repository.search(keyword).flowOn(Dispatchers.IO)
    }
}