package com.gigauri.reptiledb.module.feature.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gigauri.reptiledb.module.common.Const
import com.gigauri.reptiledb.module.core.domain.common.Resource
import com.gigauri.reptiledb.module.core.domain.usecase.analytics.AnalyticsLogger
import com.gigauri.reptiledb.module.core.domain.usecase.app.GetAppLanguage
import com.gigauri.reptiledb.module.core.domain.usecase.reptiles.GetAllReptiles
import com.gigauri.reptiledb.module.core.domain.usecase.reptiles.GetNearbyReptiles
import com.gigauri.reptiledb.module.feature.home.domain.model.Category
import com.gigauri.reptiledb.module.feature.home.domain.usecase.GetCategories
import com.gigauri.reptiledb.module.feature.home.event.HomeScreenEvent
import com.gigauri.reptiledb.module.feature.home.state.HomePageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategories: GetCategories,
    private val getNearbyReptiles: GetNearbyReptiles,
    private val getAllReptiles: GetAllReptiles,
    private val getLanguage: GetAppLanguage,
    val analytics: AnalyticsLogger
) : ViewModel() {

    val language: Flow<String?> get() = getLanguage.execute()

    private val _state = MutableStateFlow(HomePageState())
    val state: StateFlow<HomePageState> = _state

    private val _category: MutableStateFlow<Category?> = MutableStateFlow(null)
    val category: StateFlow<Category?> = _category

    init {
        analytics.logPage("HomeScreen", null)
    }

    fun onEvent(e: HomeScreenEvent) {
        when (e) {
            is HomeScreenEvent.Load -> load()
            is HomeScreenEvent.SelectCategory -> setCategory(e.category)
            is HomeScreenEvent.UpdateLocation -> setLocation(e)
            is HomeScreenEvent.ClearErrors -> clearErrors()
        }
    }

    private fun clearErrors() = viewModelScope.launch {
        _state.update { it.copy(error = null) }
    }

    private fun load() = viewModelScope.launch {
        loadCategories()
        loadNearby()
        loadAll()
        _state.update { it.copy(isInit = true) }
    }

    private suspend fun loadCategories() = viewModelScope.launch {
        _state.update { it.copy(isCategoriesLoading = true) }
        when (val categories = getCategories.execute()) {
            is Resource.Success -> {
                if (category.value == null) _category.update { categories.data.firstOrNull() }
                _state.update {
                    it.copy(categories = categories.data)
                }
            }

            is Resource.Error -> {
                _state.update { it.copy(error = categories.errorType) }
            }

            else -> Unit
        }
        _state.update { it.copy(isCategoriesLoading = false) }
    }

    private suspend fun loadNearby() = viewModelScope.launch {
        _state.update { it.copy(isNearbyLoading = true) }
        state.value.location?.let { location ->
            when (val nearbyReptiles =
                getNearbyReptiles.execute(
                    location.latitude,
                    location.longitude,
                    category.value?.id
                )
            ) {
                is Resource.Success -> {
                    _state.update { it.copy(nearbyReptiles = nearbyReptiles.data) }
                }

                is Resource.Error -> {
                    _state.update { it.copy(error = nearbyReptiles.errorType) }
                    analytics.logEvent(
                        Const.Event.NEARBY_SPECIES_NOT_FOUND,
                        mapOf("reason" to nearbyReptiles.errorType.toString())
                    )
                }

                else -> Unit
            }
        }
        _state.update { it.copy(isNearbyLoading = false) }
    }

    private suspend fun loadAll() = viewModelScope.launch {
        _state.update { it.copy(isAllReptilesLoading = true) }
        val allReptiles = getAllReptiles.execute(
            1,
            150,
            reptileType = category.value?.id
        )

        when (allReptiles) {
            is Resource.Success -> {
                _state.update { it.copy(allReptiles = allReptiles.data) }
            }

            is Resource.Error -> {
                _state.update { it.copy(error = allReptiles.errorType) }
            }

            else -> Unit
        }
        _state.update { it.copy(isAllReptilesLoading = false) }
    }

    private fun setCategory(c: Category) = viewModelScope.launch {
        _category.update { c }
        loadNearby()
        loadAll()
        analytics.logEvent(
            Const.Event.SELECT_CATEGORY,
            mapOf("category_id" to c.id)
        )
    }

    private fun setLocation(e: HomeScreenEvent.UpdateLocation) = viewModelScope.launch {
        _state.update { it.copy(location = e.location, region = e.region) }
        loadNearby()
    }
}