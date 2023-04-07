package com.example.popularlibrary.ui.user.user_repos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.popularlibrary.R
import com.example.popularlibrary.databinding.RepoItemBinding
import com.example.popularlibrary.ui.user.ReposItemView

class RepoViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent,false)
), ReposItemView {
    private val binding = RepoItemBinding.bind(itemView)
    override var pos: Int = -1

    override fun setRepoName(name: String) {
        binding.repoName.text = name
    }
}