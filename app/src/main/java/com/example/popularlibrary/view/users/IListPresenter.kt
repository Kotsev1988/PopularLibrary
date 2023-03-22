package com.example.popularlibrary.view.users

interface IListPresenter <V: IItemView> {

    var itemClickListener: ((V) -> Unit)?

    fun bindView(view: V)

    fun getCount():Int


}