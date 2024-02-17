package com.gigauri.reptiledb.module.feature.search.domain.repository

import androidx.paging.PagingData
import com.gigauri.reptiledb.module.core.domain.model.Reptile
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun search(keyword: String): Flow<PagingData<Reptile>>
}