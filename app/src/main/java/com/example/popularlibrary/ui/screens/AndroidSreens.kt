package com.example.popularlibrary.ui

import com.example.popularlibrary.domain.users.UsersItem
import com.example.popularlibrary.ui.screens.IScreens
import com.example.popularlibrary.ui.user.UserFragment
import com.example.popularlibrary.ui.users.UsersFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens: IScreens {
    override fun users(): Screen = FragmentScreen{
        UsersFragment.newInstance()
    }

    override fun user(user: UsersItem): Screen = FragmentScreen{
        UserFragment.newInstance(user)
    }


}