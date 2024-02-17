package com.gigauri.reptiledb.module.feature.search.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.gigauri.reptiledb.module.core.data.mapper.toDomain
import com.gigauri.reptiledb.module.core.domain.model.Reptile
import com.gigauri.reptiledb.module.feature.search.data.remote.api.SearchApi
import com.gigauri.reptiledb.module.feature.search.data.remote.datasource.paging.SearchPagingDataSource
import com.gigauri.reptiledb.module.feature.search.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val api: SearchApi
) : SearchRepository {

    private val pagingConfig = PagingConfig(
        pageSize = 10,
        initialLoadSize = 10,
        prefetchDistance = 4
    )

    override suspend fun search(keyword: String): Flow<PagingData<Reptile>> {
        return Pager(
            config = pagingConfig
        ) {
            SearchPagingDataSource(
                api, keyword
            )
        }.flow.map { it.map { i -> i.toDomain() } }
    }
}