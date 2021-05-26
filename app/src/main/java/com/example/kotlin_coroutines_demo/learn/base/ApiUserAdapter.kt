package com.example.kotlin_coroutines_demo.learn.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin_coroutines_demo.R
import com.example.kotlin_coroutines_demo.data.model.ApiUser
import kotlinx.android.synthetic.main.item_layout.view.*

class ApiUserAdapter(
    private val users: ArrayList<ApiUser>
) : RecyclerView.Adapter<ApiUserAdapter.ApiUserViewHolder>() {
    class ApiUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: ApiUser) {
            itemView.textViewUserName.text = user.name
            itemView.textViewUserEmail.text = user.email
            Glide.with(itemView.imageViewAvatar.context)
                .load(user.avatar)
                .into(itemView.imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiUserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_layout,
            parent,
            false
        )

        return ApiUserViewHolder(view)
    }

    override fun onBindViewHolder(holder: ApiUserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun addData(list : List<ApiUser>) {
        users.addAll(list)
        notifyDataSetChanged()
    }
}