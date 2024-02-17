package com.gigauri.reptiledb.module.common.util.parser

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GsonParser {

    inline fun <reified T> toJson(i: T?): String {
        if (i == null) return ""
        return Gson().toJson(i)
    }

    inline fun <reified T> fromJson(s: String): T? {
        if (s.isEmpty()) return null
        return Gson().fromJson(s, object : TypeToken<T>() {})
    }
}