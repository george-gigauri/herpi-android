package com.gigauri.reptiledb.module.core.data.local.typeconverters

import androidx.room.TypeConverter
import com.gigauri.reptiledb.module.common.util.parser.GsonParser
import com.gigauri.reptiledb.module.core.domain.model.Family

class FamilyTypeConverters {

    private val parser = GsonParser()

    @TypeConverter
    fun fromFamily(f: Family?): String {
        return parser.toJson(f)
    }

    @TypeConverter
    fun toFamily(s: String): Family? {
        return parser.fromJson(s)
    }
}