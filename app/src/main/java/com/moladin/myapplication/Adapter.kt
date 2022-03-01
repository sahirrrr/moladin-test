package com.moladin.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.moladin.myapplication.core.response.DataItem
import com.moladin.myapplication.databinding.UserItemsBinding

class Adapter : RecyclerView.Adapter<Adapter.UserViewHolder>() {

    private val listUser = ArrayList<DataItem>()

    fun addUser(items: ArrayList<DataItem>) {
        listUser.clear()
        listUser.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.UserViewHolder {
        val binding = UserItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: Adapter.UserViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size

    inner class UserViewHolder(private val binding : UserItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItem) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.avatar)
                    .apply(RequestOptions())
                    .into(ivPhoto)

                tvEmail.text = data.email
                tvUserName.text = data.firstName
            }
        }
    }
}