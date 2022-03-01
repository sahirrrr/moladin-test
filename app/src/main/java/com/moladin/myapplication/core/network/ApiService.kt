package com.moladin.myapplication.core.network

import com.moladin.myapplication.core.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("api/users?page=2")
    fun getUser() : Call<UserResponse>
}