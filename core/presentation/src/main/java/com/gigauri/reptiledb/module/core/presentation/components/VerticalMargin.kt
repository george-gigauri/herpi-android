package com.gigauri.reptiledb.module.core.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun VerticalMargin(size: Dp) {
    Spacer(modifier = Modifier.height(size))
}