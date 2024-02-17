package com.gigauri.reptiledb.module.feature.home.data.repository

import com.gigauri.reptiledb.module.core.data.common.safeApiCall
import com.gigauri.reptiledb.module.core.data.local.dao.CategoryDao
import com.gigauri.reptiledb.module.core.domain.common.Resource
import com.gigauri.reptiledb.module.feature.home.data.mapper.toDomain
import com.gigauri.reptiledb.module.feature.home.data.mapper.toEntity
import com.gigauri.reptiledb.module.feature.home.data.remote.api.CategoryService
import com.gigauri.reptiledb.module.feature.home.domain.model.Category
import com.gigauri.reptiledb.module.feature.home.domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val api: CategoryService,
    private val dao: CategoryDao
) : CategoryRepository {

    override suspend fun getCategories(): Resource<List<Category>> {
        return safeApiCall { api.getReptileCategories().map { it.toDomain() } }
    }

    override suspend fun isCategoriesEmptyInDatabase(): Boolean {
        return dao.isEmpty()
    }

    override suspend fun getCategoriesFromDatabase(): List<Category> {
        return dao.getCategories().map { it.toDomain() }
    }

    override suspend fun insertCategoriesIntoDatabase(list: List<Category>) {
        dao.insertAll(list.map { it.toEntity() })
    }

    override suspend fun deleteCategories() {
        dao.deleteAll()
    }
}