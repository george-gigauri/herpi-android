package com.gigauri.reptiledb.module.core.data.local.typeconverters

import androidx.room.TypeConverter
import com.gigauri.reptiledb.module.common.util.parser.GsonParser
import com.gigauri.reptiledb.module.core.domain.model.Author

class AuthorTypeConverters {

    private val parser = GsonParser()

    @TypeConverter
    fun fromAuthor(a: Author?): String {
        return a?.let {
            parser.toJson(a)
        } ?: ""
    }

    @TypeConverter
    fun toAuthor(s: String): Author? {
        return if (s.isEmpty()) null else parser.fromJson(s)
    }
}