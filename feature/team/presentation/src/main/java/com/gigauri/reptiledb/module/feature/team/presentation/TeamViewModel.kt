package com.gigauri.reptiledb.module.feature.team.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gigauri.reptiledb.module.common.Const
import com.gigauri.reptiledb.module.core.domain.common.ErrorType
import com.gigauri.reptiledb.module.core.domain.common.Resource
import com.gigauri.reptiledb.module.core.domain.usecase.analytics.AnalyticsLogger
import com.gigauri.reptiledb.module.feature.team.domain.usecase.GetTeam
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(
    private val getTeam: GetTeam,
    val analytics: AnalyticsLogger
) : ViewModel() {

    private val _state = MutableStateFlow(TeamUiState())
    private val _error = MutableSharedFlow<ErrorType?>()
    val state: StateFlow<TeamUiState> = _state
    val error: SharedFlow<ErrorType?> = _error

    init {
        loadTeam()
        analytics.logPage("TeamScreen", null)
        analytics.logEvent(Const.Event.OPEN_TEAM, mapOf())
    }

    fun onEvent(e: TeamUiEvent) {
        when (e) {
            is TeamUiEvent.Load -> loadTeam()
            is TeamUiEvent.Expand -> expandItem(e.itemId)
        }
    }

    private fun loadTeam() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        when (val result = getTeam.execute()) {
            is Resource.Success -> {
                _state.update { it.copy(data = result.data) }
            }

            is Resource.Error -> {
                _error.emit(result.errorType)
            }

            else -> Unit
        }
        _state.update { it.copy(isLoading = false) }
    }

    private fun expandItem(id: Long) = viewModelScope.launch {
        _state.update { it.copy(expandedItemId = id) }
    }
}