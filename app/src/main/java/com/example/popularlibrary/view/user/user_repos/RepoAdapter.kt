package com.example.popularlibrary.view.user.user_repos

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.popularlibrary.view.user.presenter.IUserReposListPresenter

class RepoAdapter(private val userListRepoPresenter: IUserReposListPresenter):RecyclerView.Adapter<RepoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RepoViewHolder(parent).apply {
        itemView.setOnClickListener {
            userListRepoPresenter.onItemClickListener?.invoke(this)
        }
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        return userListRepoPresenter.bindView(holder.apply {
            pos = position
        })
    }

    override fun getItemCount(): Int = userListRepoPresenter.getCount()
}