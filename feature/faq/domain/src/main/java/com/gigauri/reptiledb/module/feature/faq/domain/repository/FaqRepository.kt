package com.gigauri.reptiledb.module.feature.faq.domain.repository

import com.gigauri.reptiledb.module.core.domain.common.Resource
import com.gigauri.reptiledb.module.feature.faq.domain.model.Faq

interface FaqRepository {

    suspend fun getFaq(): Resource<List<Faq>>
    suspend fun getAllFromDatabase(): List<Faq>
    suspend fun insertAllToDatabase(list: List<Faq>)
    suspend fun deleteAllFromDatabase()
}