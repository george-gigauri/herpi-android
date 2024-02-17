package com.gigauri.reptiledb.module.feature.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gigauri.reptiledb.module.core.domain.model.Reptile
import com.gigauri.reptiledb.module.core.domain.usecase.analytics.AnalyticsLogger
import com.gigauri.reptiledb.module.feature.search.domain.usecase.Search
import com.gigauri.reptiledb.module.feature.search.presentation.event.SearchEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val search: Search,
    val analytics: AnalyticsLogger
) : ViewModel() {

    private var job: Job? = null
    private val _keyword = MutableStateFlow("")
    val pagingData = MutableStateFlow<PagingData<Reptile>>(PagingData.empty())

    fun onEvent(e: SearchEvent) {
        when (e) {
            is SearchEvent.KeywordChange -> onKeywordChange(e.keyword)
        }
    }

    private fun onKeywordChange(s: String) {
        job?.cancelChildren()
        job = viewModelScope.launch {
            delay(500)
            _keyword.update { s }
            search.execute(s).cachedIn(viewModelScope).collectLatest {
                pagingData.emit(it)
            }
        }
    }
}