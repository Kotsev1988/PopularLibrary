package com.example.popularlibrary.view.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView
import com.example.popularlibrary.R
import com.example.popularlibrary.databinding.UserItemBinding
import com.example.popularlibrary.view.users.UserItemView

class UsersViewHolder(parent: ViewGroup):RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent,false)
    ), UserItemView {
    private val binding = UserItemBinding.bind(itemView)

    override fun setText(text: String) {
        binding.userLogin.text = text
    }

    override var pos: Int = -1

}