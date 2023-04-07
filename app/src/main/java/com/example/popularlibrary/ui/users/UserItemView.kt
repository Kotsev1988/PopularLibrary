package com.example.popularlibrary.ui.users

interface UserItemView : IItemView {

    fun setText(text: String)
    fun loadAvatar(url: String)
}