package com.gigauri.reptiledb.module.feature.reptileDetails.presentation.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.core.presentation.components.HorizontalMargin
import com.gigauri.reptiledb.module.core.presentation.components.VerticalMargin
import com.gigauri.reptiledb.module.core.presentation.components.text.PrimaryTextDarkGray
import com.gigauri.reptiledb.module.core.presentation.components.text.SecondaryTextLighterDark
import com.gigauri.reptiledb.module.feature.reptileDetails.domain.model.Distribution
import com.gigauri.reptiledb.module.feature.reptileDetails.presentation.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.JointType
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Polygon
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun ExpandedMapDialog(
    areas: List<Distribution>,
    onCancel: () -> Unit
) {

    val cameraPositionState = rememberCameraPositionState()

    LaunchedEffect(key1 = Unit, block = {
        cameraPositionState.move(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(41.817667, 44.003333),
                5.7f
            )
        )
    })

    Dialog(
        onDismissRequest = { onCancel() },
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(HerpiColors.White)
        ) {

            // Map
            GoogleMap(
                cameraPositionState = cameraPositionState,
                properties = MapProperties(
                    mapType = MapType.TERRAIN,
                ),
                uiSettings = MapUiSettings(
                    compassEnabled = true,
                    myLocationButtonEnabled = true,
                ),
                modifier = Modifier
                    .fillMaxSize()
            ) {

                areas.map {
                    if (it.coordinates.isNotEmpty()) {
                        Polygon(
                            points = it.coordinates.map { c -> LatLng(c.lat, c.lng) },
                            fillColor = HerpiColors.DarkGreenMain.copy(alpha = 0.5f),
                            strokeColor = HerpiColors.DarkGreenMain,
                            strokeWidth = 12f,
                            geodesic = true,
                            strokeJointType = JointType.ROUND
                        )
                    }
                }
            }

            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopStart)
                    .background(HerpiColors.DarkGreenMain)
                    .padding(vertical = 16.dp)
                    .padding(start = 2.dp, end = 16.dp)
            ) {
                // Back Button
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = null,
                    tint = HerpiColors.White,
                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape)
                        .clickable { onCancel() }
                        .padding(12.dp)
                )

                // Title
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    PrimaryTextDarkGray(
                        text = stringResource(id = R.string.title_expanded_distribution),
                        color = HerpiColors.White
                    )
                    VerticalMargin(size = 2.dp)
                    SecondaryTextLighterDark(
                        text = stringResource(id = R.string.subtitle_expanded_distribution),
                        color = HerpiColors.White.copy(alpha = 0.7f)
                    )
                }
                HorizontalMargin(size = 12.dp)
            }
        }
    }
}