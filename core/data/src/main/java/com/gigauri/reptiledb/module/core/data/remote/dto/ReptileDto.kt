package com.gigauri.reptiledb.module.core.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ReptileDto(
    @SerializedName("id") val id: Long,
    @SerializedName("addedBy") val addedBy: AuthorDto?,
    @SerializedName("family") val family: FamilyDto?,
    @SerializedName("image") val image: String?,
    @SerializedName("transparentThumbnail") val transparentThumbnail: String?,
    @SerializedName("name") val name: String,
    @SerializedName("scientificName") val scientificName: String,
    @SerializedName("type") val type: String,
    @SerializedName("venomous") val venomous: Boolean,
    @SerializedName("hasMildVenom") val hasMildVenom: Boolean,
    @SerializedName("hasRedFlag") val hasRedFlag: Boolean,
    @SerializedName("views") val views: Int
)