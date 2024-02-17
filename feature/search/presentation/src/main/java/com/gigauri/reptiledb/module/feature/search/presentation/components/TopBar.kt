package com.gigauri.reptiledb.module.feature.search.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.core.presentation.components.HorizontalMargin

@Composable
fun TopBar(
    onBackClick: () -> Unit,
    onSearchTextChange: (String) -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 16.dp)
    ) {

        // Button Back
        HorizontalMargin(size = 2.dp)
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = null,
            tint = HerpiColors.White,
            modifier = Modifier
                .size(54.dp)
                .clip(CircleShape)
                .clickable { onBackClick() }
                .padding(14.dp)
        )
        HorizontalMargin(size = 4.dp)

        // Search Box
        SearchEditText(
            onTextChange = onSearchTextChange,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        HorizontalMargin(size = 16.dp)
    }

}