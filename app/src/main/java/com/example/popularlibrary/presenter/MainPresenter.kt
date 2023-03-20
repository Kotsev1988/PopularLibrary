package com.example.popularlibrary.presenter

import com.example.popularlibrary.model.CounterModel
import com.example.popularlibrary.view.MainView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MainPresenter: MvpPresenter<MainView>() {

    private val model = CounterModel()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setFirstButton("0")
        viewState.setSecondButton("0")
        viewState.setThirdButton("0")
    }

    fun counterClickFirst(){
        val nextValue = model.next(0)
        viewState.setFirstButton(nextValue.toString())
    }

    fun counterClickSecond() {
        val nextValue = model.next(1)
        viewState.setSecondButton(nextValue.toString())
    }

    fun counterClickThird() {
        val nextValue = model.next(2)
        viewState.setThirdButton(nextValue.toString())
    }

}