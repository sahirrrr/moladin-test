package com.moladin.myapplication.core

import androidx.paging.PagingSource
import androidx.paging.PagingState

import com.moladin.myapplication.core.network.ApiService
import com.moladin.myapplication.core.response.DataItem

class UserPagingSource(private val apiService: ApiService) : PagingSource<Int, DataItem>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
         return try {
            val currentPage = params.key ?: 1
            val response = apiService.getUser(currentPage)
            val responseData = mutableListOf<DataItem>()
            val data = response.body()?.data ?: emptyList()
            responseData.addAll(data)

             LoadResult.Page(
                data = responseData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return null
    }
}