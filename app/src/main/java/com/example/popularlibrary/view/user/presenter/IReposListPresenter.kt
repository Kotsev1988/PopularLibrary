package com.example.popularlibrary.view.user.presenter

import com.example.popularlibrary.view.user.IItemReposView

interface IReposListPresenter<V: IItemReposView> {

    var onItemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int

}