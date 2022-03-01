package com.moladin.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.moladin.myapplication.databinding.ActivityMainBinding
import com.moladin.myapplication.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var userAdapter : Adapter
    private lateinit var mainViewModel : UserViewModel
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rvUser = binding.rvUser
        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserViewModel::class.java)
        mainViewModel.setUser()
        mainViewModel.getUser().observe(this) { user ->
            if (user != null) {
                userAdapter.addUser(user)
            }
        }

        rvUser.layoutManager = LinearLayoutManager(this)
        userAdapter = Adapter()
        rvUser.adapter = userAdapter

        rvUser.setHasFixedSize(true)
    }
}