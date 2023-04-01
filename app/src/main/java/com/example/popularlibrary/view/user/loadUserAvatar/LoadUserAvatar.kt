package com.example.popularlibrary.view.user.loadUserAvatar

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

class LoadUserAvatar : ILoadUserAvatar<AppCompatImageView> {
    override fun loadUserImage(url: String, container: AppCompatImageView) {
        Glide.with(container.context)
            .load(url)
            .into(container)
    }
}