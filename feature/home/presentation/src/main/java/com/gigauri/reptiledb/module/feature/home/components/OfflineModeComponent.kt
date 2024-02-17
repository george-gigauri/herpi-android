package com.gigauri.reptiledb.module.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.feature.home.R

@Composable
@Preview
fun OfflineModeComponent(
    textSize: TextUnit = 14.sp,
    modifier: Modifier = Modifier
) {

    Text(
        text = stringResource(id = R.string.label_offline_mode),
        fontSize = textSize,
        color = HerpiColors.White,
        fontWeight = FontWeight.SemiBold,
        style = TextStyle(
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        ),
        modifier = Modifier
            .clip(RoundedCornerShape(100))
            .background(HerpiColors.NormalRed)
            .padding(horizontal = 12.dp, vertical = 10.dp)
            .then(modifier)
    )
}