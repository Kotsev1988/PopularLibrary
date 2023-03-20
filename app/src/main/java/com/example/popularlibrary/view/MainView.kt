package com.example.popularlibrary.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)

interface MainView: MvpView{

    fun setFirstButton(text: String)

    fun setSecondButton(text: String)

    fun setThirdButton(text: String)
}