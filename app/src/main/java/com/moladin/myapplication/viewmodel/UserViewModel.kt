package com.moladin.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moladin.myapplication.core.network.ApiConfig
import com.moladin.myapplication.core.response.DataItem
import com.moladin.myapplication.core.response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    private val listUser = MutableLiveData<ArrayList<DataItem>>()
    private val TAG = UserViewModel::class.java.simpleName

    fun getUser() :  LiveData<ArrayList<DataItem>> = listUser

    fun setUser() {
        val client = ApiConfig.getApiService().getUser()

        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val dataArray = response.body()?.data as ArrayList<DataItem>
                    listUser.postValue(dataArray)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d(TAG, "Something unexpected happened to our request")
                t.printStackTrace()
            }
        })
    }
}