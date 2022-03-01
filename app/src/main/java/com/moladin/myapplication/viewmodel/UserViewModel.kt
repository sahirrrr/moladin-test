package com.moladin.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.moladin.myapplication.core.UserPagingSource
import com.moladin.myapplication.core.network.ApiService

class UserViewModel(private val apiService: ApiService) : ViewModel() {

    val listData = Pager(PagingConfig(pageSize = 6)) {
        UserPagingSource(apiService)
    }.flow.cachedIn(viewModelScope)
}