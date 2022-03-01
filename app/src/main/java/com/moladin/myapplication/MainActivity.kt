package com.moladin.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.moladin.myapplication.core.network.ApiConfig
import com.moladin.myapplication.core.network.ApiService
import com.moladin.myapplication.databinding.ActivityMainBinding
import com.moladin.myapplication.viewmodel.UserViewModel
import com.moladin.myapplication.viewmodel.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.subscribe
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var userAdapter : Adapter
    private lateinit var viewModel : UserViewModel
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =  ViewModelProvider(this, ViewModelFactory(ApiService.getApiService()))[UserViewModel::class.java]

        val rvUser = binding.rvUser

        userAdapter = Adapter()
        rvUser.layoutManager = LinearLayoutManager(this)
        rvUser.adapter = userAdapter
        rvUser.setHasFixedSize(true)

        lifecycleScope.launch {
            viewModel.listData.collect  {
                userAdapter.submitData(it)
            }
        }
    }
}