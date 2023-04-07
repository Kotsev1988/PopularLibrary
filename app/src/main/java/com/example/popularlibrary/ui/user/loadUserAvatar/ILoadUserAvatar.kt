package com.example.popularlibrary.ui.user.loadUserAvatar

interface ILoadUserAvatar<T> {
    fun loadUserImage(url: String, container: T)
}