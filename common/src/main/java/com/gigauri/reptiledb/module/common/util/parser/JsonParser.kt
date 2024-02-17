package com.gigauri.reptiledb.module.common.util.parser

interface JsonParser<T> {
    fun toJson(i: T?): String
    fun fromJson(s: String): T?
}