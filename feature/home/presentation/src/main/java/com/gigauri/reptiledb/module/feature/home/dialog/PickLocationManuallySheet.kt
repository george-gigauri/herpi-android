package com.gigauri.reptiledb.module.feature.home.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsIgnoringVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.core.presentation.components.HorizontalMargin
import com.gigauri.reptiledb.module.core.presentation.components.VerticalMargin
import com.gigauri.reptiledb.module.core.presentation.components.text.PrimaryTextDarkGray
import com.gigauri.reptiledb.module.core.presentation.components.text.SecondaryTextLighterDark
import com.gigauri.reptiledb.module.feature.home.R
import org.osmdroid.api.IMapController
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.gestures.OneFingerZoomOverlay

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PickLocationManuallySheet(
    onPick: (GeoPoint) -> Unit,
    onCancel: () -> Unit
) {

    val density = LocalDensity.current
    var center: GeoPoint by remember { mutableStateOf(GeoPoint(41.817667, 44.003333)) }
    var markerLatLng: GeoPoint? by rememberSaveable { mutableStateOf(null) }

    var mapController: IMapController? = remember { null }

    Dialog(
        onDismissRequest = { onCancel() },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = true
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(24.dp))
                .background(HerpiColors.White)
                .padding(horizontal = 16.dp)
                .padding(
                    top = 20.dp,
                    bottom = WindowInsets.systemBars.getBottom(density).dp + 85.dp
                )
        ) {

            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // Title
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    PrimaryTextDarkGray(
                        text = stringResource(id = R.string.title_pick_custom_location)
                    )
                    VerticalMargin(size = 2.dp)
                    SecondaryTextLighterDark(
                        text = stringResource(id = R.string.subtitle_pick_custom_location)
                    )
                }
                HorizontalMargin(size = 12.dp)

                // Close icon
                Icon(
                    painter = painterResource(id = R.drawable.ic_close_circle),
                    contentDescription = null,
                    tint = HerpiColors.PinkRed,
                    modifier = Modifier
                        .size(42.dp)
                        .clickable { onCancel() }
                        .align(Alignment.Top)
                )
            }
            VerticalMargin(size = 12.dp)

            // Map
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
            ) {
                AndroidView(
                    update = { mapView ->
                        mapView.overlays.clear()
                        mapView.overlays.add(MapEventsOverlay(object : MapEventsReceiver {
                            override fun singleTapConfirmedHelper(p: GeoPoint?): Boolean {
                                markerLatLng = p
                                return true
                            }

                            override fun longPressHelper(p: GeoPoint?): Boolean = false
                        }))

                        markerLatLng?.let {
                            mapView.overlays.add(Marker(mapView).apply {
                                position = it
                            })
                        }
                    },
                    factory = {
                        org.osmdroid.views.MapView(it).apply {
                            setTileSource(TileSourceFactory.MAPNIK)
                            setMultiTouchControls(true)
                            mapController = controller
                            mapController?.setZoom(8.5)
                            mapController?.animateTo(center)
                            overlays.add(OneFingerZoomOverlay())
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }

            VerticalMargin(size = 16.dp)

            // Buttons
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Understood Button
                Text(
                    text = stringResource(id = R.string.btn_cancel),
                    color = HerpiColors.DarkGreenMain,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clip(RoundedCornerShape(100))
                        .background(Color.Transparent)
                        .clickable { onCancel() }
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .weight(1f)
                )
                HorizontalMargin(size = 16.dp)

                // Check Settings Button
                Text(
                    text = stringResource(id = R.string.btn_complete),
                    color = HerpiColors.White,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clip(RoundedCornerShape(100))
                        .background(HerpiColors.DarkGreenMain)
                        .clickable { markerLatLng?.let(onPick) }
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .weight(1f)
                )
            }
        }
    }
}