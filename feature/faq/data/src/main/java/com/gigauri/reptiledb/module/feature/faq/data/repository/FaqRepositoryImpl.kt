package com.gigauri.reptiledb.module.feature.faq.data.repository

import com.gigauri.reptiledb.module.core.data.common.safeApiCall
import com.gigauri.reptiledb.module.core.domain.common.Resource
import com.gigauri.reptiledb.module.feature.faq.data.local.dao.FaqDao
import com.gigauri.reptiledb.module.feature.faq.data.mapper.toDomain
import com.gigauri.reptiledb.module.feature.faq.data.mapper.toEntity
import com.gigauri.reptiledb.module.feature.faq.data.remote.api.FaqApi
import com.gigauri.reptiledb.module.feature.faq.domain.model.Faq
import com.gigauri.reptiledb.module.feature.faq.domain.repository.FaqRepository
import javax.inject.Inject

class FaqRepositoryImpl @Inject constructor(
    private val api: FaqApi,
    private val dao: FaqDao
) : FaqRepository {

    override suspend fun getFaq(): Resource<List<Faq>> {
        return safeApiCall { api.getFaq().map { it.toDomain() } }
    }

    override suspend fun getAllFromDatabase(): List<Faq> {
        return dao.getAll().map { it.toDomain() }
    }

    override suspend fun insertAllToDatabase(list: List<Faq>) {
        dao.insertAll(list.map { it.toEntity() })
    }

    override suspend fun deleteAllFromDatabase() {
        dao.deleteAll()
    }
}