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

@Composable
fun PrimaryTextDarkGray(
    text: String,
    modifier: Modifier = Modifier,
    size: TextUnit = 16.sp,
    maxLines: Int = 2,
    color: Color = HerpiColors.DarkGray,
    alignment: TextAlign = TextAlign.Start,
    weight: FontWeight = FontWeight.SemiBold
) {
    Text(
        text = text,
        fontSize = size,
        fontWeight = weight,
        color = color,
        textAlign = alignment,
        style = TextStyle(
            platformStyle = PlatformTextStyle(
                includeFontPadding = false
            )
        ),
        maxLines = maxLines,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.then(modifier)
    )
}