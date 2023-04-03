package com.example.popularlibrary.view.screens

import com.example.popularlibrary.domain.users.UsersItem
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun user(user: UsersItem): Screen
}