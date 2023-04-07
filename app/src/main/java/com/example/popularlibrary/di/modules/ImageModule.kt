package com.example.popularlibrary.di.modules

import androidx.appcompat.widget.AppCompatImageView
import com.example.popularlibrary.ui.users.loadImage.GlideImageLoader
import com.example.popularlibrary.ui.users.loadImage.IImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {

    @Singleton
    @Provides
    fun imageLoader() : IImageLoader<AppCompatImageView> = GlideImageLoader()

}