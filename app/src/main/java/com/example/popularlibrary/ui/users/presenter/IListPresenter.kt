package com.example.popularlibrary.ui.users.presenter

import com.example.popularlibrary.ui.users.IItemView
import io.reactivex.rxjava3.subjects.PublishSubject

interface IListPresenter<V : IItemView> {
    val itemClickStream: PublishSubject<V>

    var itemClickListener: ((V) -> Unit)?

    fun bindView(view: V)

    fun getCount(): Int


}