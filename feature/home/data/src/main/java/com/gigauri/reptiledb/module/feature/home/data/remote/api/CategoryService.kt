package com.gigauri.reptiledb.module.feature.home.data.remote.api

import com.gigauri.reptiledb.module.feature.home.data.remote.dto.CategoryDto
import retrofit2.http.GET

interface CategoryService {

    @GET("api/v1/reptiles/categories")
    suspend fun getReptileCategories(): List<CategoryDto>
}