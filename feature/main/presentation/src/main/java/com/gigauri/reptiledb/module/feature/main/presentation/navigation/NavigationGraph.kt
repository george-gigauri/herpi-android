package com.gigauri.reptiledb.module.feature.main.presentation.navigation

import android.app.Activity
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.gigauri.reptiledb.module.common.Const
import com.gigauri.reptiledb.module.common.util.IntentUtil
import com.gigauri.reptiledb.module.common.util.SettingsUtil
import com.gigauri.reptiledb.module.feature.faq.presentation.FaqScreen
import com.gigauri.reptiledb.module.feature.home.page.HomeScreen
import com.gigauri.reptiledb.module.feature.reptileDetails.presentation.pages.ReptileDetailsScreen
import com.gigauri.reptiledb.module.feature.search.presentation.pages.SearchScreen
import com.gigauri.reptiledb.module.feature.team.presentation.TeamScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    onNavDrawerClick: () -> Unit
) {

    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Pages.Home.pageId
    ) {

        composable(
            Pages.Home.pageId,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "${Const.HERPI_URL}"
                    action = Intent.ACTION_VIEW
                }
            )
        ) {
            HomeScreen(
                onNavDrawerClick = onNavDrawerClick,
                onSearchBoxClick = {
                    navController.navigate(Pages.Search.pageId)
                },
                openReptileDetail = {
                    navController.navigate(Pages.ReptileDetails.pageId + "/${it.id}")
                }
            )
        }

        composable(
            Pages.ReptileDetails.pageId + "/{id}",
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "${Const.HERPI_URL}/reptiles/{id}/details"
                    action = Intent.ACTION_VIEW
                }
            ),
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                    defaultValue = -1
                }
            )
        ) { entry ->
            val id = entry.arguments?.getLong("id")
            ReptileDetailsScreen(
                viewModel = hiltViewModel(),
                deepLinkId = id,
                onBackClick = { navController.navigateUp() }
            )
        }

        composable(Pages.Contact.pageId) {
            (context as? Activity)?.let { activity ->
                IntentUtil.openUrlToBrowserPopup(activity, Const.Url.CONTACT)
            }
            navController.navigateUp()
        }

        composable(Pages.Team.pageId) {
            TeamScreen(
                onDrawerClick = onNavDrawerClick,
                onChatClick = {
                    (context as? Activity)?.let(SettingsUtil::openMessengerChat)
                }
            )
        }

        composable(
            Pages.Faq.pageId,
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "${Const.HERPI_URL}/faq"
                    action = Intent.ACTION_VIEW
                }
            )
        ) {
            FaqScreen(
                onDrawerClick = onNavDrawerClick,
                onChatClick = {
                    (context as? Activity)?.let(SettingsUtil::openMessengerChat)
                }
            )
        }

        composable(Pages.Search.pageId) {
            SearchScreen(
                onBackClick = {
                    navController.navigateUp()
                },
                onReptileClick = {
                    navController.navigate(Pages.ReptileDetails.pageId + "/${it.id}")
                }
            )
        }
    }
}