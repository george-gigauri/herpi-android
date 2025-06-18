package com.gigauri.reptiledb.module.feature.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gigauri.reptiledb.module.core.presentation.components.VerticalMargin
import com.gigauri.reptiledb.module.core.presentation.components.text.PrimaryTextDarkGray
import com.gigauri.reptiledb.module.core.presentation.components.text.SecondaryTextLighterDark
import com.gigauri.reptiledb.module.feature.home.R

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun NearbySpeciesTitle() {
    Column {
        PrimaryTextDarkGray(
            text = stringResource(id = R.string.title_nearby_species),
            modifier = Modifier.padding(horizontal = 32.dp)
        )
        VerticalMargin(size = 4.dp)
        SecondaryTextLighterDark(
            text = stringResource(id = R.string.subtitle_nearby_species),
            modifier = Modifier.padding(horizontal = 32.dp)
        )
    }
}