package com.gigauri.reptiledb.module.core.data.di.module

import com.gigauri.reptiledb.module.core.data.repository.AppDataStoreRepositoryImpl
import com.gigauri.reptiledb.module.core.data.repository.ReptileRepositoryImpl
import com.gigauri.reptiledb.module.core.domain.repository.AppDataStoreRepository
import com.gigauri.reptiledb.module.core.domain.repository.ReptileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindReptileRepo(repo: ReptileRepositoryImpl): ReptileRepository

    @Binds
    abstract fun bindAppDataStoreRepo(repo: AppDataStoreRepositoryImpl): AppDataStoreRepository
}