package ge.gigauri.herpi.feature.herpetogallery.data.remote.api

import ge.gigauri.herpi.feature.herpetogallery.data.remote.dto.HerpetogalleryResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface HerpetogalleryApi {
    @GET("api/v1/gallery")
    suspend fun getGallery(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
        @Query("category") category: String? = null,
        @Query("venom") venom: String? = null,
        @Query("redList") redList: Boolean? = null
    ): HerpetogalleryResponseDto
}
