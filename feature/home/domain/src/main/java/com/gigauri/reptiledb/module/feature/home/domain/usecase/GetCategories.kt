package com.gigauri.reptiledb.module.feature.home.domain.usecase

import com.gigauri.reptiledb.module.core.domain.common.Resource
import com.gigauri.reptiledb.module.feature.home.domain.model.Category
import com.gigauri.reptiledb.module.feature.home.domain.repository.CategoryRepository
import javax.inject.Inject

class GetCategories @Inject constructor(
    private val repository: CategoryRepository
) {

    suspend fun execute(): Resource<List<Category>> {
        return repository.getCategories().let {
            if (it is Resource.Success) {
                repository.deleteCategories()
                repository.insertCategoriesIntoDatabase(it.data)
            }
            Resource.Success(repository.getCategoriesFromDatabase())
        }
    }
}