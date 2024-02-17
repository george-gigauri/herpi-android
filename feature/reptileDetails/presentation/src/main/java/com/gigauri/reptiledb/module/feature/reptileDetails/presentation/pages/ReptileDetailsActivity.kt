package com.gigauri.reptiledb.module.feature.reptileDetails.presentation.pages

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.gigauri.reptiledb.module.common.extensions.loadLocate
import com.gigauri.reptiledb.module.core.presentation.HerpiDefaultTheme
import com.gigauri.reptiledb.module.feature.reptileDetails.presentation.viewModels.ReptileDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class ReptileDetailsActivity : ComponentActivity() {

    private val viewModel: ReptileDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        runBlocking { viewModel.appLanguage.firstOrNull()?.let(::loadLocate) }
        setContent {
            HerpiDefaultTheme {
                ReptileDetailsScreen(viewModel)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.analytics.logPage(
            this.javaClass.simpleName,
            this.javaClass
        )
    }
}