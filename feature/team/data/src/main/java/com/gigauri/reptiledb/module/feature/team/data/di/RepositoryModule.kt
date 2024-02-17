package com.gigauri.reptiledb.module.feature.team.data.di

import com.gigauri.reptiledb.module.feature.team.data.repository.TeamRepositoryImpl
import com.gigauri.reptiledb.module.feature.team.domain.repository.TeamRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindTeamRepository(repo: TeamRepositoryImpl): TeamRepository
}