package com.gigauri.reptiledb.module.core.domain.repository

interface Analytics {
    fun logEvent(eventName: String, params: Map<String, Any?>)
    fun logPage(name: String, c: Class<*>?)
}