package com.gigauri.reptiledb.module.core.data.local.typeconverters

import androidx.room.TypeConverter
import com.gigauri.reptiledb.module.common.util.parser.GsonParser
import com.gigauri.reptiledb.module.core.domain.model.GalleryItem

class GalleryTypeConverters {

    private val parser = GsonParser()

    @TypeConverter
    fun fromGallery(l: List<GalleryItem>): String {
        return parser.toJson(l)
    }

    @TypeConverter
    fun toGallery(s: String): List<GalleryItem> {
        return parser.fromJson(s) ?: emptyList()
    }
}