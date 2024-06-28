package com.gigauri.reptiledb.module.feature.reptileDetails.presentation.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gigauri.reptiledb.module.common.Const
import com.gigauri.reptiledb.module.core.domain.common.ErrorType
import com.gigauri.reptiledb.module.core.domain.common.Resource
import com.gigauri.reptiledb.module.core.domain.usecase.analytics.AnalyticsLogger
import com.gigauri.reptiledb.module.core.domain.usecase.app.GetAppLanguage
import com.gigauri.reptiledb.module.feature.reptileDetails.domain.usecase.GetDetails
import com.gigauri.reptiledb.module.feature.reptileDetails.domain.usecase.GetDistribution
import com.gigauri.reptiledb.module.feature.reptileDetails.presentation.event.ReptileDetailsEvent
import com.gigauri.reptiledb.module.feature.reptileDetails.presentation.state.ReptileDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReptileDetailsViewModel @Inject constructor(
    private val getDetails: GetDetails,
    private val getDistribution: GetDistribution,
    private val getLanguage: GetAppLanguage,
    private val savedStateHandle: SavedStateHandle,
    val analytics: AnalyticsLogger
) : ViewModel() {

    val appLanguage: Flow<String?> get() = getLanguage.execute()

    private var reptileId: Long = savedStateHandle[Const.Key.KEY_REPTILE_ID] ?: -1

    private val _state = MutableStateFlow(ReptileDetailsState())
    val state: StateFlow<ReptileDetailsState> = _state

    init {
        analytics.logEvent(
            Const.Event.OPEN_DETAILS,
            mapOf("specie_id" to reptileId)
        )
    }

    fun onEvent(e: ReptileDetailsEvent) {
        when (e) {
            is ReptileDetailsEvent.Load -> load(e)
            is ReptileDetailsEvent.ClearErrors -> clearErrors()
            else -> Unit
        }
    }

    private fun clearErrors() = viewModelScope.launch {
        _state.update { it.copy(error = null) }
    }

    private fun load(e: ReptileDetailsEvent.Load) = viewModelScope.launch {
        e.defaultReptileId?.let { reptileId = it }
        loadDetails()
        loadDistribution()
    }

    private suspend fun loadDetails() {
        val details = getDetails.execute(reptileId)
        when (details) {
            is Resource.Success -> {
                _state.update { it.copy(data = details.data, error = null) }
            }

            is Resource.Error -> {
                if (details.errorType !is ErrorType.Network) {
                    _state.update { it.copy(error = details.errorType) }
                }
            }

            else -> Unit
        }
    }

    private suspend fun loadDistribution() {
        val data = getDistribution.execute(reptileId)
        when (data) {
            is Resource.Success -> {
                _state.update { it.copy(distribution = data.data, error = null) }
            }

            is Resource.Error -> {
                if (data.errorType !is ErrorType.Network) {
                    _state.update { it.copy(error = data.errorType) }
                }
            }

            else -> Unit
        }
    }
}