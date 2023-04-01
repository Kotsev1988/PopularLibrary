package com.example.popularlibrary.view.users.loadImage

import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

class GlideImageLoader: IImageLoader<AppCompatImageView> {
    override fun loadAvatar(url: String, container: AppCompatImageView) {
        Glide.with(container.context)
            .load(url)
            .into(container)
    }
}