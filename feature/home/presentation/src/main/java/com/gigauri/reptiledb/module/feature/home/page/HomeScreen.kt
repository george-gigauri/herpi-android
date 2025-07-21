package com.gigauri.reptiledb.module.feature.home.page

import android.Manifest
import android.app.Activity
import android.location.Location
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gigauri.reptiledb.module.common.Const
import com.gigauri.reptiledb.module.common.extensions.getCityAndRegion
import com.gigauri.reptiledb.module.common.util.NetworkMonitor
import com.gigauri.reptiledb.module.common.util.NetworkUtil
import com.gigauri.reptiledb.module.common.util.SettingsUtil
import com.gigauri.reptiledb.module.common.util.location.LocationUtil
import com.gigauri.reptiledb.module.core.domain.model.Reptile
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.core.presentation.bottomsheet.ErrorBottomSheet
import com.gigauri.reptiledb.module.core.presentation.components.HorizontalMargin
import com.gigauri.reptiledb.module.core.presentation.components.VerticalMargin
import com.gigauri.reptiledb.module.feature.home.components.AllSpeciesTitle
import com.gigauri.reptiledb.module.feature.home.components.Category
import com.gigauri.reptiledb.module.feature.home.components.NearbyReptile
import com.gigauri.reptiledb.module.feature.home.components.NearbySpeciesNotFound
import com.gigauri.reptiledb.module.feature.home.components.NearbySpeciesTitle
import com.gigauri.reptiledb.module.feature.home.components.Reptile
import com.gigauri.reptiledb.module.feature.home.components.SearchBox
import com.gigauri.reptiledb.module.feature.home.components.TopBar
import com.gigauri.reptiledb.module.feature.home.components.shimmer.CategoryShimmer
import com.gigauri.reptiledb.module.feature.home.components.shimmer.NearbySpeciesShimmer
import com.gigauri.reptiledb.module.feature.home.dialog.LocationNotAvailableBottomSheet
import com.gigauri.reptiledb.module.feature.home.dialog.LocationPermissionInformationDialog
import com.gigauri.reptiledb.module.feature.home.dialog.PickLocationManuallySheet
import com.gigauri.reptiledb.module.feature.home.event.HomeScreenEvent
import com.gigauri.reptiledb.module.feature.home.state.HomePageState
import com.gigauri.reptiledb.module.feature.home.viewmodel.HomeViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    onNavDrawerClick: () -> Unit,
    onSearchBoxClick: () -> Unit,
    openReptileDetail: (Reptile) -> Unit
) {
    val context = LocalContext.current
    var isInternetAvailable: Boolean by rememberSaveable {
        mutableStateOf(
            NetworkUtil.isInternetAvailable(
                context
            )
        )
    }
    val viewModel: HomeViewModel = hiltViewModel()
    val locale: String? by viewModel.language.collectAsStateWithLifecycle(initialValue = null)
    val state: HomePageState by viewModel.state.collectAsStateWithLifecycle()
    val notificationPermission =
        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
    val locationPermission = rememberPermissionState(
        permission = Manifest.permission.ACCESS_FINE_LOCATION
    )
    var isLocationPermissionInfoDialogVisible by rememberSaveable { mutableStateOf(false) }
    var isLocationUnavailableDialogVisible by rememberSaveable { mutableStateOf(false) }
    var isPickLocationManuallySheetVisible by rememberSaveable { mutableStateOf(false) }
    val nearbyState = rememberLazyGridState()

    DisposableEffect(key1 = Unit, effect = {
        val nm = NetworkMonitor(context)
        nm.onOnline { isInternetAvailable = true }
        nm.onOffline {
            viewModel.analytics.logEvent(Const.Event.OPEN_IN_OFFLINE_MODE, mapOf())
            isInternetAvailable = false
        }
        nm.registerNetworkCallback()
        onDispose { nm.unregisterNetworkCallback() }
    })

    fun requestLocation() {
        LocationUtil.getMyLocation(context, onSuccess = {
            viewModel.onEvent(
                HomeScreenEvent.UpdateLocation(
                    it,
                    it.getCityAndRegion(context, locale)
                )
            )
        })
    }

    LaunchedEffect(key1 = Unit, block = {
        if (!state.isInit) {
            if (notificationPermission.status != PermissionStatus.Granted) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    notificationPermission.launchPermissionRequest()
                }
            }
            isLocationPermissionInfoDialogVisible =
                locationPermission.status != PermissionStatus.Granted
            isLocationUnavailableDialogVisible =
                !LocationUtil.isLocationTurnedOn(context) && locationPermission.status == PermissionStatus.Granted

            viewModel.onEvent(HomeScreenEvent.Load)
        }
    })

    LaunchedEffect(key1 = locationPermission.status, block = {
        if (LocationUtil.isLocationTurnedOn(context) && locationPermission.status == PermissionStatus.Granted) {
            requestLocation()
        }
    })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(HerpiColors.DarkGreenMain)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // TopBar
            TopBar(
                isOfflineMode = !isInternetAvailable,
                currentLocation = state.region,
                onDrawerClick = { onNavDrawerClick() },
                onChatClick = {
                    (context as? Activity)?.let(SettingsUtil::openMessengerChat)
                    viewModel.analytics.logEvent(
                        Const.Event.CLICK_CHAT,
                        mapOf()
                    )
                },
                onLocationClick = {
                    isPickLocationManuallySheetVisible = true
                    viewModel.analytics.logEvent(
                        Const.Event.CLICK_PICK_LOCATION_MANUALLY,
                        mapOf()
                    )
                }
            )

            // White Container
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(HerpiColors.White)
            ) {
                // Search Box
                if (isInternetAvailable) {
                    item { VerticalMargin(size = 24.dp) }
                    item {
                        SearchBox(
                            onClick = onSearchBoxClick,
                            modifier = Modifier.padding(horizontal = 24.dp)
                        )
                    }
                }

                // Categories
                item { VerticalMargin(24.dp) }
                item {
                    Row(
                        modifier = Modifier.horizontalScroll(rememberScrollState())
                    ) {
                        HorizontalMargin(22.dp)
                        if (state.isCategoriesLoading) {
                            (0..8).map {
                                CategoryShimmer()
                                HorizontalMargin(size = 12.dp)
                            }
                        } else {
                            (state.categories).map {
                                Category(
                                    iconUrl = it.iconUrl ?: "",
                                    title = it.titleTurned,
                                    isSelected = it == viewModel.category.value,
                                    onClick = { viewModel.onEvent(HomeScreenEvent.SelectCategory(it)) }
                                )
                                HorizontalMargin(12.dp)
                            }
                        }
                        HorizontalMargin(20.dp)
                    }
                }

                // Nearby Species
                if (isInternetAvailable) {
                    item { VerticalMargin(size = 28.dp) }
                    item { NearbySpeciesTitle() }
                    item { VerticalMargin(size = 2.dp) }
                    if (state.nearbyReptiles.isEmpty() && !state.isNearbyLoading) {
                        item { NearbySpeciesNotFound() }
                    } else {
                        if (state.isNearbyLoading) {
                            items(10) {
                                NearbySpeciesShimmer()
                            }
                        } else {
                            item { VerticalMargin(size = 12.dp) }
                            items(state.nearbyReptiles) {
                                Reptile(
                                    it,
                                    onClick = {
                                        openReptileDetail(it)
                                        viewModel.analytics.logEvent(
                                            Const.Event.OPEN_NEARBY_SPECIE_DETAILS,
                                            mapOf("specie_id" to it.id)
                                        )
                                    }
                                )
                                VerticalMargin(size = 12.dp)
                            }
                        }
                    }
                }
                item { VerticalMargin(size = 12.dp) }

                // All Species
                item { VerticalMargin(size = 16.dp) }
                item { AllSpeciesTitle() }
                item { VerticalMargin(size = 16.dp) }
                items(state.allReptiles) {
                    Reptile(
                        reptile = it,
                        onClick = { openReptileDetail(it) }
                    )
                    VerticalMargin(size = 12.dp)
                }
            }
        }
    }

// Location Permission information dialog
    if (isLocationPermissionInfoDialogVisible) {
        LocationPermissionInformationDialog(
            onUnderstoodClick = {
                isLocationPermissionInfoDialogVisible = false
                locationPermission.launchPermissionRequest()
            },
            onCancel = {
                isLocationPermissionInfoDialogVisible = false
            }
        )
    }

// Location Unavailable dialog
    if (isLocationUnavailableDialogVisible) {
        LocationNotAvailableBottomSheet(
            onSettingsClick = {
                isLocationUnavailableDialogVisible = false
                LocationUtil.requestLocation((context as Activity)) {
                    requestLocation()
                }
            },
            onCancel = {
                isLocationUnavailableDialogVisible = false
            }
        )
    }

    if (isPickLocationManuallySheetVisible) {
        PickLocationManuallySheet(
            onPick = {
                isPickLocationManuallySheetVisible = false
                Location(null).apply {
                    latitude = it.latitude
                    longitude = it.longitude
                }.let {
                    viewModel.onEvent(
                        HomeScreenEvent.UpdateLocation(
                            it,
                            it.getCityAndRegion(context)
                        )
                    )
                    // Log Event
                    viewModel.analytics.logEvent(
                        Const.Event.PICK_LOCATION_MANUALLY,
                        mapOf()
                    )
                }
            },
            onCancel = { isPickLocationManuallySheetVisible = false }
        )
    }

    state.error?.let {
        ErrorBottomSheet(
            title = it.toString(),
            message = it.toString(),
            onDismiss = { viewModel.onEvent(HomeScreenEvent.ClearErrors) },
            positiveButton = { viewModel.onEvent(HomeScreenEvent.ClearErrors) }) {
        }
    }
}

