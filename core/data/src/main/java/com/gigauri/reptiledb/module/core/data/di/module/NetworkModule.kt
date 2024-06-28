package com.gigauri.reptiledb.module.core.data.di.module

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.gigauri.reptiledb.module.common.Const
import com.gigauri.reptiledb.module.core.data.local.preferences.PreferencesDataStore
import com.gigauri.reptiledb.module.core.data.remote.api.ReptileAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(Const.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(context: Context, dataStore: PreferencesDataStore): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor.Builder(context).build())
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .cache(Cache(
                context.cacheDir,
                50 * 1024 * 1024
            ))
            .addInterceptor { chain ->
                val language: String = runBlocking { dataStore.getLanguage().first() ?: "ka" }
                val original: Request = chain.request()
                val requestBuilder: Request.Builder =
                    original.newBuilder().url(chain.request().url())
                requestBuilder.addHeader("Accept-Language", language)
                val request: Request = requestBuilder.build()
                chain.proceed(request)
            }.build()

    @Provides
    fun provideReptileApi(retrofit: Retrofit): ReptileAPI = retrofit.create(ReptileAPI::class.java)
}