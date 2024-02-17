package com.gigauri.reptiledb.module.core.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gigauri.reptiledb.module.core.presentation.HerpiColors

@Composable
fun DefaultCircularLoader(
    modifier: Modifier = Modifier
) {

    CircularProgressIndicator(
        color = HerpiColors.DarkGreenMain,
        modifier = Modifier
            .size(36.dp)
            .then(modifier)
    )
}