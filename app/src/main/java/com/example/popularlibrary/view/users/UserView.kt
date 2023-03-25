package com.example.popularlibrary.view.users

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView : MvpView {

    fun init()
    fun updateList()
    fun onError(t: Throwable)

}