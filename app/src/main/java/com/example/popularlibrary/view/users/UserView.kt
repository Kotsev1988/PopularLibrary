package com.example.popularlibrary.view.users

import com.example.popularlibrary.utlis.Publisher
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView : MvpView {


    fun init()
    fun updateList()
    fun onError(t: Throwable)

}