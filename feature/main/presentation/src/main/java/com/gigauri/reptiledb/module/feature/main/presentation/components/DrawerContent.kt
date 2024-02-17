package com.gigauri.reptiledb.module.feature.main.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gigauri.reptiledb.module.common.AppLanguages
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.feature.main.presentation.navigation.Pages

@Composable
fun DrawerContent(
    menu: List<Pages>,
    selectedItemId: String,
    selectedLanguageCode: String?,
    onMenuItemClick: (pageId: String) -> Unit,
    onLanguageClick: (AppLanguages) -> Unit
) {
    Column(
        modifier = Modifier
            .width(320.dp)
            .fillMaxHeight()
            .clip(RoundedCornerShape(topEnd = 32.dp, bottomEnd = 32.dp))
            .background(
                HerpiColors.DarkGreenMain,
                RoundedCornerShape(topEnd = 32.dp, bottomEnd = 32.dp)
            )
    ) {

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        ) {


            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {

                menu.forEach {
                    DrawerItem(
                        text = stringResource(id = it.titleRes),
                        isSelected = it.pageId == selectedItemId,
                        onClick = { onMenuItemClick(it.pageId) }
                    )
                }
            }
        }

        // Settings
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {

            // Language Switcher
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(100))
                    .background(HerpiColors.White.copy(alpha = 0.4f))
            ) {

                AppLanguages.entries.mapIndexed { index, item ->
                    val isLast = AppLanguages.entries.lastIndex == index
                    val isSelected = selectedLanguageCode == item.code

                    Text(
                        text = item.name,
                        color = if (isSelected) HerpiColors.CreamyYellow else HerpiColors.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .clickable { onLanguageClick(item) }
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    )

                    if (!isLast) {
                        Spacer(
                            modifier = Modifier
                                .width(2.dp)
                                .height(IntrinsicSize.Max)
                                .padding(vertical = 2.dp)
                                .background(HerpiColors.DarkGray)
                        )
                    }
                }
            }
        }
    }
}