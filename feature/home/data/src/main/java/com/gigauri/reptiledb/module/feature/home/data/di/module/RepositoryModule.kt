package com.gigauri.reptiledb.module.feature.home.data.di.module

import com.gigauri.reptiledb.module.feature.home.data.repository.CategoryRepositoryImpl
import com.gigauri.reptiledb.module.feature.home.domain.repository.CategoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCategoryRepository(repo: CategoryRepositoryImpl): CategoryRepository
}