package com.gigauri.reptiledb.module.feature.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.core.presentation.components.HorizontalMargin
import com.gigauri.reptiledb.module.core.presentation.components.VerticalMargin
import com.gigauri.reptiledb.module.core.presentation.components.text.PrimaryTextDarkGray
import com.gigauri.reptiledb.module.core.presentation.components.text.SecondaryTextLighterDark
import com.gigauri.reptiledb.module.feature.home.R

@Composable
fun TopBar(
    isOfflineMode: Boolean,
    currentLocation: String?,
    onDrawerClick: () -> Unit,
    onChatClick: () -> Unit,
    onLocationClick: () -> Unit
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
        HorizontalMargin(size = 16.dp)

        // Location
        if (!isOfflineMode) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { onLocationClick() }
            ) {
                SecondaryTextLighterDark(
                    text = stringResource(id = R.string.label_region),
                    color = HerpiColors.White.copy(alpha = .7f)
                )
                VerticalMargin(size = 4.dp)
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Location icon
                    Icon(
                        painter = painterResource(id = R.drawable.ic_location_tick),
                        contentDescription = null,
                        tint = HerpiColors.White,
                        modifier = Modifier.size(20.dp)
                    )
                    HorizontalMargin(size = 8.dp)

                    // Current Location
                    PrimaryTextDarkGray(
                        text = currentLocation
                            ?: stringResource(id = R.string.error_cant_retrieve_location),
                        color = HerpiColors.White,
                        maxLines = 1
                    )
                }
            }
        } else {
            Box(modifier = Modifier.weight(1f)) {
                OfflineModeComponent(
                    textSize = 14.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        HorizontalMargin(size = 16.dp)

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
        )
        HorizontalMargin(size = 3.dp)
    }
}