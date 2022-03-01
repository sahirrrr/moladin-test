package com.moladin.myapplication.core.network

import com.moladin.myapplication.core.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUser(@Query("page") page: Int) : Response<UserResponse>

}