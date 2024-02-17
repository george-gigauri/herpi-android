package com.gigauri.reptiledb.module.feature.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gigauri.reptiledb.module.common.Const
import com.gigauri.reptiledb.module.core.domain.usecase.analytics.AnalyticsLogger
import com.gigauri.reptiledb.module.core.domain.usecase.app.GetAppLanguage
import com.gigauri.reptiledb.module.core.domain.usecase.app.SetLanguage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getLanguage: GetAppLanguage,
    private val setLanguage: SetLanguage,
    val analytics: AnalyticsLogger
) : ViewModel() {

    val language = getLanguage.execute()

    fun onEvent(e: MainEvent) {
        when (e) {
            is MainEvent.SetLanguage -> setLanguage(e)
        }
    }

    private fun setLanguage(e: MainEvent.SetLanguage) = viewModelScope.launch {
        setLanguage.execute(e.language.code)
        analytics.logEvent(Const.Event.SET_LANGUAGE, hashMapOf("language_code" to e.language.code))
    }
}