package com.example.popularlibrary.view.screens

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun user(login: String): Screen
}