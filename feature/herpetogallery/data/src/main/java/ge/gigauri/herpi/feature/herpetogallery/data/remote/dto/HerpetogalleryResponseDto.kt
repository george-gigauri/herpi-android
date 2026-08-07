package ge.gigauri.herpi.feature.herpetogallery.data.remote.dto

import com.gigauri.reptiledb.module.core.data.remote.dto.AuthorDto
import com.gigauri.reptiledb.module.core.data.remote.dto.ReptileDto
import com.google.gson.annotations.SerializedName

data class HerpetogalleryResponseDto(
    @SerializedName("data") val data: List<HerpetogalleryItemDto>,
    @SerializedName("page") val page: Int,
    @SerializedName("pageSize") val pageSize: Int,
    @SerializedName("totalPages") val totalPages: Int
)

data class HerpetogalleryItemDto(
    @SerializedName("id") val id: Long,
    @SerializedName("reptile") val reptile: ReptileDto,
    @SerializedName("image") val image: HerpetogalleryImageDto
)

data class HerpetogalleryImageDto(
    @SerializedName("id") val id: Long,
    @SerializedName("url") val url: String,
    @SerializedName("credits") val credits: List<String>?,
    @SerializedName("author") val author: AuthorDto?
)
