package com.example.popularlibrary.ui.users.loadImage

interface IImageLoader<T> {
    fun loadAvatar(url: String, container: T)
}