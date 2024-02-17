package com.gigauri.reptiledb.module.feature.main.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gigauri.reptiledb.module.core.presentation.HerpiColors

@Composable
fun DrawerItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    Text(
        text = text,
        color = if (isSelected) HerpiColors.CreamyYellow else HerpiColors.White,
        fontSize = 15.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(100))
            .clickable { onClick() }
            .padding(horizontal = 24.dp, vertical = 10.dp)

    )
}