package com.gigauri.reptiledb.module.feature.team.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.core.presentation.components.HorizontalMargin
import com.gigauri.reptiledb.module.core.presentation.components.text.PrimaryTextDarkGray
import com.gigauri.reptiledb.module.core.presentation.components.text.SecondaryTextLighterDark
import com.gigauri.reptiledb.module.feature.team.presentation.R

@Composable
fun TopBar(
    onDrawerClick: () -> Unit,
    onChatClick: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 16.dp)
    ) {

        // Drawer Burger Icon
        HorizontalMargin(size = 1.dp)
        Icon(
            painter = painterResource(id = R.drawable.ic_menu_burger),
            contentDescription = null,
            tint = HerpiColors.White,
            modifier = Modifier
                .size(54.dp)
                .clip(CircleShape)
                .clickable { onDrawerClick() }
                .padding(15.dp)
        )
        HorizontalMargin(size = 24.dp)


        // Herpi Logo
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_herpi),
                contentDescription = "herpi logo",
                modifier = Modifier
                    .size(54.dp)
                    .padding(12.dp)
            )
            Column {
                PrimaryTextDarkGray(
                    text = "HERPI",
                    color = HerpiColors.White,
                )
                SecondaryTextLighterDark(
                    text = "herpi.ge",
                    size = 11.sp,
                    color = HerpiColors.White.copy(alpha = 0.7f)
                )
            }
            HorizontalMargin(size = 16.dp)
        }
        HorizontalMargin(size = 12.dp)

        // Chat Button
        Icon(
            painter = painterResource(id = R.drawable.ic_chat_unread),
            contentDescription = null,
            tint = HerpiColors.White,
            modifier = Modifier
                .size(54.dp)
                .clip(CircleShape)
                .clickable { onChatClick() }
                .padding(12.dp)
                .align(Alignment.CenterVertically)
        )
        HorizontalMargin(size = 3.dp)
    }
}