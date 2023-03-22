package com.example.popularlibrary.view.user

interface IReposListPresenter<V: IItemReposView> {

    var onItemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int

}