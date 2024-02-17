package com.gigauri.reptiledb.module.feature.reptileDetails.data.remote.dto

import com.gigauri.reptiledb.module.core.data.remote.dto.AuthorDto
import com.gigauri.reptiledb.module.core.data.remote.dto.FamilyDto
import com.google.gson.annotations.SerializedName

data class ReptileDetailsDto(
    @SerializedName("addedBy") val addedBy: AuthorDto,
    @SerializedName("createdAt") val createdAt: Long,
    @SerializedName("description") val description: String,
    @SerializedName("family") val family: FamilyDto,
    @SerializedName("gallery") val gallery: List<GalleryDto>,
    @SerializedName("genus") val genus: String,
    @SerializedName("hasMildVenom") val hasMildVenom: Boolean,
    @SerializedName("hasRedFlag") val hasRedFlag: Boolean,
    @SerializedName("id") val id: Long,
    @SerializedName("image") val image: String?,
    @SerializedName("name") val name: String,
    @SerializedName("publishedAt") val publishedAt: Long,
    @SerializedName("scientificName") val scientificName: String,
    @SerializedName("status") val status: String,
    @SerializedName("transparentThumbnail") val transparentThumbnail: String?,
    @SerializedName("type") val type: String,
    @SerializedName("venomous") val venomous: Boolean,
    @SerializedName("views") val views: Int
)
