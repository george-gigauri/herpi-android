package com.gigauri.reptiledb.module.feature.faq.data.di

import android.content.Context
import androidx.room.Room
import com.gigauri.reptiledb.module.feature.faq.data.local.FaqDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideFaqDatabase(
        context: Context
    ): FaqDatabase = Room.databaseBuilder(
        context,
        FaqDatabase::class.java,
        FaqDatabase.DATABASE_NAME
    ).build()
}