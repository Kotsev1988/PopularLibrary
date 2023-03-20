package com.example.popularlibrary.presenter

import com.example.popularlibrary.model.CounterModel
import com.example.popularlibrary.view.MainView

class MainPresenter(private var view: MainView) {

    private val model = CounterModel()


    fun counterClickFirst(){
        val nextValue = model.next(0)
        view.setFirstButton(nextValue.toString())
    }

    fun counterClickSecond() {
        val nextValue = model.next(1)
        view.setSecondButton(nextValue.toString())
    }

    fun counterClickThird() {
        val nextValue = model.next(2)
        view.setThirdButton(nextValue.toString())
    }

}