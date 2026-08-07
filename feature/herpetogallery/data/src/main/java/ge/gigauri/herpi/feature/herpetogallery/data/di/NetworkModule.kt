package ge.gigauri.herpi.feature.herpetogallery.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ge.gigauri.herpi.feature.herpetogallery.data.remote.api.HerpetogalleryApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHerpetogalleryApi(retrofit: Retrofit): HerpetogalleryApi {
        return retrofit.create(HerpetogalleryApi::class.java)
    }
}
