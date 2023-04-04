package com.example.popularlibrary.view.user


import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ProfileView : MvpView {
    fun init()
    fun setName(text: String)
    fun setAvatar(url: String)
    fun onError(e: Throwable)
    fun updateList()
    fun setRepoDateOnClick(date: String, forks: Int)
}