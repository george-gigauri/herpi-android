package com.gigauri.reptiledb.module.feature.home.dialog

import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.Rect
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
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
import org.osmdroid.events.MapListener
import org.osmdroid.events.ScrollEvent
import org.osmdroid.events.ZoomEvent
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.TilesOverlay
import org.osmdroid.views.overlay.gestures.OneFingerZoomOverlay
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay
import org.osmdroid.views.overlay.mylocation.DirectedLocationOverlay

@Composable
fun PickLocationManuallySheet(
    onPick: (GeoPoint) -> Unit,
    onCancel: () -> Unit
) {

    val context = LocalContext.current
    var center: GeoPoint by remember { mutableStateOf(GeoPoint(1.0, 1.0)) }
    var markerLatLng: GeoPoint? by rememberSaveable { mutableStateOf(null) }
    var locationString by rememberSaveable { mutableStateOf(".") }

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
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(HerpiColors.White)
                .padding(horizontal = 16.dp)
                .padding(top = 20.dp, bottom = 16.dp)
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
            Box(modifier = Modifier.fillMaxSize()) {
                AndroidView(
                    update = {

                    },
                    factory = {
                        org.osmdroid.views.MapView(it).apply {
                            this.setTileSource(TileSourceFactory.MAPNIK)
                            this.getLocalVisibleRect(Rect())
                            this.setMultiTouchControls(true)


                            // Adjust the alpha of the TilesOverlay to dim the map
                            val tilesOverlay: TilesOverlay = overlayManager.tilesOverlay
                            tilesOverlay.setColorFilter(
                                PorterDuffColorFilter(
                                    Color(0x1A000000).toArgb(),
                                    PorterDuff.Mode.DARKEN
                                )
                            )

                            mapController = this.controller
                            mapController?.animateTo(center)
                            mapController?.setZoom(5.5)

                            this.overlays.add(OneFingerZoomOverlay())
                            this.overlays.add(RotationGestureOverlay(this))
                            this.overlays.add(DirectedLocationOverlay(context))

                            this.addMapListener(object : MapListener {
                                override fun onScroll(event: ScrollEvent?): Boolean {
                                    return true
                                }

                                override fun onZoom(event: ZoomEvent?): Boolean {
                                    return true
                                }
                            })
                        }
                    })
            }

            // Display Picked Location
            VerticalMargin(size = 16.dp)
            PrimaryTextDarkGray(
                text = locationString,
                maxLines = 2,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
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