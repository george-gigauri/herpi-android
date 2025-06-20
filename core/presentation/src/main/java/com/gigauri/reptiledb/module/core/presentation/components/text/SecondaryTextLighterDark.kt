package com.gigauri.reptiledb.module.core.presentation.components.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import kotlin.math.max

@Composable
fun SecondaryTextLighterDark(
    text: String,
    modifier: Modifier = Modifier,
    size: TextUnit = 13.sp,
    color: Color = HerpiColors.LightGray,
    alignment: TextAlign = TextAlign.Start,
    maxLines: Int = Int.MAX_VALUE
) {
    Text(
        text = text,
        fontSize = size,
        fontWeight = FontWeight.Normal,
        textAlign = alignment,
        color = color,
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        style = TextStyle(
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        ),
        modifier = Modifier.then(modifier)
    )
}