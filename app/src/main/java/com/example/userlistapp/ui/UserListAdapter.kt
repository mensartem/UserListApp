package com.example.userlistapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.userlistapp.R
import com.example.userlistapp.data.datasource.Person
import com.example.userlistapp.databinding.ItemUserInfoBinding


class UserListAdapter :
    ListAdapter<Person, UserListAdapter.UserViewHolder>(
        diffUtil
    ) {

    companion object {
        private val diffUtil: DiffUtil.ItemCallback<Person> =
            object : DiffUtil.ItemCallback<Person>() {
                override fun areItemsTheSame(
                    oldItem: Person,
                    newItem: Person,
                ): Boolean = oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: Person,
                    newItem: Person,
                ): Boolean = oldItem.fullName == newItem.fullName
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_user_info,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    class UserViewHolder(val binding: ItemUserInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: Person) {
            binding.itemViewEntity = UserListItemViewEntity(user)
        }
    }


}