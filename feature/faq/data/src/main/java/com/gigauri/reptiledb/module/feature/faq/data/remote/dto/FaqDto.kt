package com.gigauri.reptiledb.module.feature.faq.data.remote.dto

import com.google.gson.annotations.SerializedName

data class FaqDto(
    @SerializedName("id") val id: Long,
    @SerializedName("question") val question: String,
    @SerializedName("answer") val answer: String
)