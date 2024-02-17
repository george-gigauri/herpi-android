package com.gigauri.reptiledb.module.feature.home.domain.repository

import com.gigauri.reptiledb.module.core.domain.common.Resource
import com.gigauri.reptiledb.module.feature.home.domain.model.Category

interface CategoryRepository {
    suspend fun getCategories(): Resource<List<Category>>
    suspend fun deleteCategories()
    suspend fun isCategoriesEmptyInDatabase(): Boolean
    suspend fun getCategoriesFromDatabase(): List<Category>

    suspend fun insertCategoriesIntoDatabase(list: List<Category>)
}