package com.example.popularlibrary.view

import com.example.popularlibrary.view.screens.IScreens
import com.example.popularlibrary.view.user.UserFragment
import com.example.popularlibrary.view.users.UsersFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import kotlin.math.log

class AndroidScreens: IScreens {
    override fun users(): Screen = FragmentScreen{
        UsersFragment.newInstance()

    }

    override fun user(login: String): Screen = FragmentScreen{
        UserFragment.newInstance(login)
    }


}