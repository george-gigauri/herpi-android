package com.gigauri.reptiledb.module.feature.reptileDetails.presentation.components

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.Rect
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.core.presentation.R
import com.gigauri.reptiledb.module.feature.reptileDetails.domain.model.Distribution
import org.osmdroid.api.IMapController
import org.osmdroid.events.MapListener
import org.osmdroid.events.ScrollEvent
import org.osmdroid.events.ZoomEvent
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Polygon
import org.osmdroid.views.overlay.TilesOverlay
import org.osmdroid.views.overlay.gestures.OneFingerZoomOverlay
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay
import org.osmdroid.views.overlay.mylocation.DirectedLocationOverlay

@Composable
fun DistributionCard(
    data: List<Distribution>,
    onTouch: () -> Unit,
    modifier: Modifier = Modifier
) {

    var mapController: IMapController? = remember { null }

    AndroidView(
        update = {
            data.map { polygon ->
                it.overlays.add(Polygon().apply {
                    this.fillColor = HerpiColors.PinkRed.copy(alpha = 0.35f).toArgb()
                    this.strokeColor = HerpiColors.PinkRed.toArgb()

                    polygon.coordinates.map { coordinates ->
                        GeoPoint(coordinates.lat, coordinates.lng)
                    }.forEach {
                        this.addPoint(it)
                    }
                })
            }
        },
        factory = {
            org.osmdroid.views.MapView(it).apply {
                this.setTileSource(TileSourceFactory.MAPNIK)
                this.getLocalVisibleRect(Rect())
                this.setMultiTouchControls(true)

                mapController = this.controller
                mapController?.animateTo(GeoPoint(41.817667, 44.003333))
                mapController?.setZoom(7.75)

                this.overlays.add(DirectedLocationOverlay(context))
            }
        },
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(RoundedCornerShape(16.dp))
            .then(modifier)
    )
}