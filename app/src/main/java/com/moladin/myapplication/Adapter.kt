package com.moladin.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.moladin.myapplication.core.response.DataItem
import com.moladin.myapplication.databinding.UserItemsBinding

class Adapter : PagingDataAdapter<DataItem, Adapter.UserViewHolder>(DiffUtilCallBack) {

    private val listUser = ArrayList<DataItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    class UserViewHolder(private val binding : UserItemsBinding) : RecyclerView.ViewHolder(binding.root) {
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

    object DiffUtilCallBack: DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }

    }
}