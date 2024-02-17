package com.gigauri.reptiledb.module.feature.home.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.gigauri.reptiledb.module.core.domain.model.Reptile
import com.gigauri.reptiledb.module.feature.home.R

@Composable
fun Reptile.venomousTitle(): String {
    return if (venomous) {
        if (hasMildVenom) stringResource(id = R.string.mildly_venomous)
        else stringResource(id = R.string.venomous)
    } else if (hasMildVenom) {
        stringResource(id = R.string.mildly_venomous)
    } else {
        stringResource(id = R.string.nonvenomous)
    }
}