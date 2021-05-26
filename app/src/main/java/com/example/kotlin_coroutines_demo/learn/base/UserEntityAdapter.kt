package com.example.kotlin_coroutines_demo.learn.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin_coroutines_demo.R
import com.example.kotlin_coroutines_demo.data.local.UserEntity
import kotlinx.android.synthetic.main.item_layout.view.*

class UserEntityAdapter(
    private val users: ArrayList<UserEntity>
) : RecyclerView.Adapter<UserEntityAdapter.UserEntityViewHolder>() {
    class UserEntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: UserEntity) {
            itemView.textViewUserName.text = user.name
            itemView.textViewUserEmail.text = user.email
            Glide.with(itemView.imageViewAvatar.context)
                .load(user.avatar)
                .into(itemView.imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserEntityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_layout,
            parent,
            false
        )
        return UserEntityViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserEntityViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun addData(list: List<UserEntity>) {
        users.addAll(list)
        notifyDataSetChanged()
    }
}