package com.example.popularlibrary.view.users.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.popularlibrary.view.users.presenter.IUsersListPresenter

class UsersAdapter(private val presenter: IUsersListPresenter) :
    RecyclerView.Adapter<UsersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder =
        UsersViewHolder(parent).apply {

            itemView.setOnClickListener {
                presenter.itemClickStream.onNext(this)
            }
        }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount(): Int = presenter.getCount()

}