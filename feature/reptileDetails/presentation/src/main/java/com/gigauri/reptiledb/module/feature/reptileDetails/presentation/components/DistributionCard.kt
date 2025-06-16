package com.gigauri.reptiledb.module.feature.reptileDetails.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.feature.reptileDetails.domain.model.Distribution

@Composable
fun DistributionCard(
    data: List<Distribution>,
    onTouch: () -> Unit,
    modifier: Modifier = Modifier
) {

//    val cameraPositionState = rememberCameraPositionState()
//
//    LaunchedEffect(key1 = Unit, block = {
//        cameraPositionState.move(
//            CameraUpdateFactory.newLatLngZoom(
//                LatLng(41.817667, 44.003333),
//                5.7f
//            )
//        )
//    })
//
//    GoogleMap(
//        uiSettings = MapUiSettings(
//            compassEnabled = false,
//            indoorLevelPickerEnabled = false,
//            mapToolbarEnabled = false,
//            myLocationButtonEnabled = false,
//            rotationGesturesEnabled = false,
//            scrollGesturesEnabled = false,
//            scrollGesturesEnabledDuringRotateOrZoom = false,
//            tiltGesturesEnabled = false,
//            zoomControlsEnabled = false,
//            zoomGesturesEnabled = false
//        ),
//        cameraPositionState = cameraPositionState,
//        properties = MapProperties(
//            mapType = MapType.TERRAIN,
//        ),
//        modifier = Modifier
//            .then(modifier)
//            .fillMaxWidth()
//            .wrapContentHeight()
//            .aspectRatio(1f)
//            .clip(RoundedCornerShape(16.dp))
//            .clickable { onTouch() }
//    ) {
//
//        data.map {
//            if (it.coordinates.isNotEmpty()) {
//                Polygon(
//                    points = it.coordinates.map { c -> LatLng(c.lat, c.lng) },
//                    fillColor = HerpiColors.DarkGreenMain.copy(alpha = 0.5f),
//                    strokeColor = HerpiColors.DarkGreenMain,
//                    strokeWidth = 12f,
//                    geodesic = true,
//                    strokeJointType = JointType.ROUND
//                )
//            }
//        }
//    }
}