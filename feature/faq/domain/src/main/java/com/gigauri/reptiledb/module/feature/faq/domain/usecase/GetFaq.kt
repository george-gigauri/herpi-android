package com.gigauri.reptiledb.module.feature.faq.domain.usecase

import com.gigauri.reptiledb.module.core.domain.common.Resource
import com.gigauri.reptiledb.module.feature.faq.domain.model.Faq
import com.gigauri.reptiledb.module.feature.faq.domain.repository.FaqRepository
import javax.inject.Inject

class GetFaq @Inject constructor(
    private val repository: FaqRepository
) {

    suspend fun execute(): Resource<List<Faq>> {
        return repository.getFaq().let {
            when (it) {
                is Resource.Success -> {
                    repository.deleteAllFromDatabase()
                    repository.insertAllToDatabase(it.data)
                    Resource.Success(repository.getAllFromDatabase())
                }

                is Resource.Error -> {
                    Resource.Error(it.errorType, repository.getAllFromDatabase())
                }

                else -> {
                    Resource.Success(emptyList())
                }
            }
        }
    }
}