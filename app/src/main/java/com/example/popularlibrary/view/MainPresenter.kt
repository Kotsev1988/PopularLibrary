package com.example.popularlibrary.view

import com.example.popularlibrary.view.*
import com.example.popularlibrary.view.screens.IScreens
import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainPresenter(private val router: Router, private val screens: IScreens) : MvpPresenter<MainView>() {


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked(){
        router.exit()
    }
}