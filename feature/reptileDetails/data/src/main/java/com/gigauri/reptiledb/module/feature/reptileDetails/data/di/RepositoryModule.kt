package com.gigauri.reptiledb.module.feature.reptileDetails.data.di

import com.gigauri.reptiledb.module.feature.reptileDetails.data.repository.ReptileDetailsRepositoryImpl
import com.gigauri.reptiledb.module.feature.reptileDetails.domain.repository.ReptileDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindDetailsRepository(repo: ReptileDetailsRepositoryImpl): ReptileDetailsRepository
}