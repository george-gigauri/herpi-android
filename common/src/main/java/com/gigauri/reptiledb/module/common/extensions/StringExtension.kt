package com.gigauri.reptiledb.module.common.extensions

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

fun String.copyToClipboard(context: Context) {
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText("text", this)
    clipboardManager.setPrimaryClip(clipData)
}