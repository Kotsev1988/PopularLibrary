package com.example.popularlibrary.ui.user.presenter

import com.example.popularlibrary.ui.user.IItemReposView

interface IReposListPresenter<V: IItemReposView> {

    var onItemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int

}