package com.example.popularlibrary.ui.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.popularlibrary.databinding.UserItemBinding
import com.example.popularlibrary.ui.users.loadImage.IImageLoader
import com.example.popularlibrary.ui.users.UserItemView
import com.example.popularlibrary.ui.users.presenter.IUsersListPresenter

class UsersAdapter(
    private val presenter: IUsersListPresenter,
    val imageLoader: IImageLoader<AppCompatImageView>,
) :
    RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder =
        UsersViewHolder(UserItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false)).apply {

            itemView.setOnClickListener {
                presenter.itemClickStream.onNext(this)
            }
        }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount(): Int = presenter.getCount()

    inner class UsersViewHolder(private val vb: UserItemBinding) : RecyclerView.ViewHolder(vb.root),
        UserItemView {

        override fun setText(text: String) {
            vb.userLogin.text = text
        }

        override fun loadAvatar(url: String) {
            imageLoader.loadAvatar(url, vb.avatar)
        }

        override var pos: Int = -1

    }
}