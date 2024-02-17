package com.gigauri.reptiledb.module.feature.search.data.remote.datasource.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gigauri.reptiledb.module.core.data.remote.dto.ReptileDto
import com.gigauri.reptiledb.module.feature.search.data.remote.api.SearchApi
import retrofit2.HttpException

class SearchPagingDataSource(
    private val api: SearchApi,
    private val keyword: String
) : PagingSource<Int, ReptileDto>() {

    override fun getRefreshKey(state: PagingState<Int, ReptileDto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReptileDto> {
        val position = params.key ?: 1
        val response = api.search(
            keyword = keyword,
            page = position,
            pageSize = params.loadSize
        )

        return try {
            LoadResult.Page(
                response.data,
                if (position == 1) null else position - 1,
                if (response.data.isEmpty()) null else position + 1
            )
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}