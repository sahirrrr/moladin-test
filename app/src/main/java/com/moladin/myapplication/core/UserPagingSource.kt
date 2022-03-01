package com.moladin.myapplication.core

import androidx.paging.PagingSource
import androidx.paging.PagingState

import com.moladin.myapplication.core.network.ApiService
import com.moladin.myapplication.core.response.DataItem

class UserPagingSource(private val apiService: ApiService) : PagingSource<Int, DataItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
         try {
            val currentLoadingPageKey = params.key ?: 1
            val response = apiService.getUser(currentLoadingPageKey)
            val responseData = mutableListOf<DataItem>()
            val data = response.body()?.data ?: emptyList()
            responseData.addAll(data)

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition
    }
}