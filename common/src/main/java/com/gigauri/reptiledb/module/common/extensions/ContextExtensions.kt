package com.gigauri.reptiledb.module.common.extensions

import android.content.Context
import java.io.IOException


fun Context.isOnline(): Boolean {
    var isOnline = false
    try {
        val runtime = Runtime.getRuntime()
        val p = runtime.exec("ping -c 1 8.8.8.8")
        val waitFor = p.waitFor()
        isOnline = waitFor == 0
    } catch (e: IOException) {
        e.printStackTrace()
    } catch (e: InterruptedException) {
        e.printStackTrace()
    }

    return isOnline
}