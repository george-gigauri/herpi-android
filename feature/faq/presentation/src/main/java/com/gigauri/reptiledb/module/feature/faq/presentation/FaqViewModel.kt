package com.gigauri.reptiledb.module.feature.faq.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gigauri.reptiledb.module.core.domain.common.ErrorType
import com.gigauri.reptiledb.module.core.domain.common.Resource
import com.gigauri.reptiledb.module.core.domain.usecase.analytics.AnalyticsLogger
import com.gigauri.reptiledb.module.feature.faq.domain.usecase.GetFaq
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FaqViewModel @Inject constructor(
    private val getFaq: GetFaq,
    val analyticsLogger: AnalyticsLogger
) : ViewModel() {

    private val _state = MutableStateFlow(FaqUiState())
    val state: StateFlow<FaqUiState> = _state

    private val _error = MutableSharedFlow<ErrorType?>()
    val error: SharedFlow<ErrorType?> = _error

    init {
        loadFaq()
    }

    fun onEvent(e: FaqUiEvent) {
        when (e) {
            is FaqUiEvent.Load -> loadFaq()
            is FaqUiEvent.Expand -> expand(e.id)
        }
    }

    private fun loadFaq() = viewModelScope.launch {
        _state.update { it.copy(isLoading = true) }
        when (val result = getFaq.execute()) {
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

    private fun expand(id: Long) = viewModelScope.launch {
        _state.update {
            it.copy(expandedItemId = if (it.expandedItemId == id) null else id)
        }
    }
}