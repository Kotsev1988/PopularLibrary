package com.example.popularlibrary.view.users.presenter

import com.example.popularlibrary.view.users.IItemView
import io.reactivex.rxjava3.subjects.PublishSubject

interface IListPresenter<V : IItemView> {
    val itemClickStream: PublishSubject<V>

    var itemClickListener: ((V) -> Unit)?

    fun bindView(view: V)

    fun getCount(): Int


}