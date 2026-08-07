package ge.gigauri.herpi.feature.herpetogallery.data.remote.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gigauri.reptiledb.module.core.domain.model.ReptileType
import ge.gigauri.herpi.feature.herpetogallery.data.mapper.toDomain
import ge.gigauri.herpi.feature.herpetogallery.data.remote.api.HerpetogalleryApi
import ge.gigauri.herpi.feature.herpetogallery.domain.model.HerpetogalleryItem
import retrofit2.HttpException

class HerpetogalleryPagingSource(
    private val api: HerpetogalleryApi,
    private val category: ReptileType? = null,
    private val venom: String? = null,
    private val redList: Boolean? = null
) : PagingSource<Int, HerpetogalleryItem>() {

    override fun getRefreshKey(state: PagingState<Int, HerpetogalleryItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, HerpetogalleryItem> {
        val position = params.key ?: 1
        return try {
            val response = api.getGallery(
                page = position,
                pageSize = params.loadSize,
                category = category?.name,
                venom = venom,
                redList = redList
            )
            val items = response.data.map { it.toDomain() }

            LoadResult.Page(
                data = items,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (items.isEmpty() || position >= response.totalPages) null else position + 1
            )
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
