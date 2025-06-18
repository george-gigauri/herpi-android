package com.gigauri.reptiledb.module.feature.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.core.presentation.components.HorizontalMargin
import com.gigauri.reptiledb.module.core.presentation.components.text.SecondaryTextLighterDark
import com.gigauri.reptiledb.module.feature.home.R

@Composable
fun SearchBox(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
            .clip(RoundedCornerShape(100))
            .clickable { onClick() }
            .background(HerpiColors.LightGray.copy(alpha = 0.15f))
            .padding(vertical = 4.dp)
    ) {

        HorizontalMargin(size = 2.dp)

        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = null,
            tint = Color(0xFF9FAEB5),
            modifier = Modifier
                .size(48.dp)
                .padding(12.dp)
        )

        HorizontalMargin(size = 8.dp)

        SecondaryTextLighterDark(
            text = stringResource(id = R.string.hint_search_here),
            size = 15.sp,
        )
    }
}