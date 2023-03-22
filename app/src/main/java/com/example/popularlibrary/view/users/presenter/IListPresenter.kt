package com.example.popularlibrary.view.users.presenter

import com.example.popularlibrary.view.users.IItemView

interface IListPresenter <V: IItemView> {

    var itemClickListener: ((V) -> Unit)?

    fun bindView(view: V)

    fun getCount():Int


}