package ge.gigauri.herpi.feature.herpetogallery.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gigauri.reptiledb.module.core.domain.model.ReptileType
import ge.gigauri.herpi.feature.herpetogallery.domain.model.HerpetogalleryItem
import ge.gigauri.herpi.feature.herpetogallery.domain.usecase.GetGalleryItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HerpetogalleryViewModel @Inject constructor(
    private val getGalleryItemsUseCase: GetGalleryItemsUseCase
) : ViewModel() {

    private val _galleryItems = MutableStateFlow<PagingData<HerpetogalleryItem>>(PagingData.empty())
    val galleryItems: StateFlow<PagingData<HerpetogalleryItem>> = _galleryItems.asStateFlow()

    private val _selectedCategory = MutableStateFlow<ReptileType?>(null)
    val selectedCategory: StateFlow<ReptileType?> = _selectedCategory.asStateFlow()

    private val _selectedVenom = MutableStateFlow<String?>(null)
    val selectedVenom: StateFlow<String?> = _selectedVenom.asStateFlow()

    private val _selectedRedList = MutableStateFlow<Boolean?>(null)
    val selectedRedList: StateFlow<Boolean?> = _selectedRedList.asStateFlow()

    init {
        loadGallery()
    }

    fun loadGallery() {
        viewModelScope.launch {
            getGalleryItemsUseCase(
                category = _selectedCategory.value,
                venom = _selectedVenom.value,
                redList = _selectedRedList.value
            ).cachedIn(viewModelScope)
                .collectLatest {
                    _galleryItems.value = it
                }
        }
    }

    fun filterByCategory(category: ReptileType?) {
        _selectedCategory.value = category
        loadGallery()
    }

    fun filterByVenom(venom: String?) {
        _selectedVenom.value = venom
        loadGallery()
    }

    fun filterByRedList(redList: Boolean?) {
        _selectedRedList.value = redList
        loadGallery()
    }
}
