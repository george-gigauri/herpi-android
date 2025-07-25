package com.gigauri.reptiledb.module.feature.main.presentation

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.systemGesturesPadding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.gigauri.reptiledb.module.common.AppLanguages
import com.gigauri.reptiledb.module.core.domain.common.LocaleManager
import com.gigauri.reptiledb.module.core.presentation.HerpiColors
import com.gigauri.reptiledb.module.feature.main.presentation.bottomsheet.ChooseApplicationLanguageSheet
import com.gigauri.reptiledb.module.feature.main.presentation.components.DrawerContent
import com.gigauri.reptiledb.module.feature.main.presentation.navigation.NavigationGraph
import com.gigauri.reptiledb.module.feature.main.presentation.navigation.Pages
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun MainScreen(
    intent: Intent,
    viewModel: MainViewModel
) {
    val activity = LocalContext.current as MainActivity
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val drawerScope = rememberCoroutineScope()
    val selectedLanguage by viewModel.language.collectAsStateWithLifecycle(initialValue = null)
    var selectedMenuItemId by remember {
        mutableStateOf(Pages.Home.pageId)
    }

    ModalNavigationDrawer(
        scrimColor = HerpiColors.DarkGreenMain.copy(alpha = 0.4f),
        drawerContent = {
            DrawerContent(
                menu = Pages.all(),
                selectedLanguageCode = selectedLanguage,
                selectedItemId = selectedMenuItemId,
                onLanguageClick = {
                    viewModel.onEvent(MainEvent.SetLanguage(it))
                    LocaleManager.currentLang = it.code
                    runBlocking { delay(250) }
                    activity.startActivity(Intent(activity, MainActivity::class.java))
                    activity.finish()
                },
                onMenuItemClick = {
                    selectedMenuItemId = it
                    navController.navigate(it)
                    drawerScope.launch {
                        delay(200)
                        drawerState.close()
                    }
                }
            )
        },
        drawerState = drawerState,
    ) {
        NavigationGraph(
            navController = navController,
            onNavDrawerClick = {
                drawerScope.launch {
                    if (drawerState.isOpen) drawerState.close()
                    else drawerState.open()
                }
            }
        )
    }

    if (selectedLanguage == null) {
        ChooseApplicationLanguageSheet(
            languages = AppLanguages.entries.toList(),
            onRemember = { viewModel.onEvent(MainEvent.SetLanguage(it)) },
            onCancel = { }
        )
    }
}