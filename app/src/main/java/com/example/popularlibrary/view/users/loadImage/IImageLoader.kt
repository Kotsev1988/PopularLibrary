package com.example.popularlibrary.view.users.loadImage

interface IImageLoader<T> {
    fun loadAvatar(url: String, container: T)
}