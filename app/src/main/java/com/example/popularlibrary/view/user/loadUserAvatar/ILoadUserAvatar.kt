package com.example.popularlibrary.view.user.loadUserAvatar

interface ILoadUserAvatar<T> {
    fun loadUserImage(url: String, container: T)
}