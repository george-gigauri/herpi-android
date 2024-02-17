package com.gigauri.reptiledb.module.feature.reptileDetails.domain.usecase

import com.gigauri.reptiledb.module.core.domain.common.ErrorType
import com.gigauri.reptiledb.module.core.domain.common.Resource
import com.gigauri.reptiledb.module.feature.reptileDetails.domain.model.ReptileFull
import com.gigauri.reptiledb.module.feature.reptileDetails.domain.repository.ReptileDetailsRepository
import javax.inject.Inject

class GetDetails @Inject constructor(
    private val repository: ReptileDetailsRepository
) {

    suspend fun execute(reptileId: Long): Resource<ReptileFull> {
        return repository.getReptileDetails(reptileId).let {
            if (it is Resource.Success) {
                repository.insertToDatabase(it.data)
            }
            repository.getByIdFromDatabase(reptileId)?.let {
                Resource.Success(it)
            } ?: Resource.Error(
                errorType = ErrorType.Generic(NullPointerException("Could not find data from database"))
            )
        }
    }
}