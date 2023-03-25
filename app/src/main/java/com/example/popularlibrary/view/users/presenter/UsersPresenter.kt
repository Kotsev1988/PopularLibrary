package com.example.popularlibrary.view.users.presenter

import com.example.popularlibrary.data.GitUsersRepoImpl
import com.example.popularlibrary.domain.UsersItem
import com.example.popularlibrary.view.screens.IScreens
import com.example.popularlibrary.view.users.UserItemView
import com.example.popularlibrary.view.users.UserView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import moxy.InjectViewState
import moxy.MvpPresenter
import java.util.concurrent.TimeUnit

@InjectViewState
class UsersPresenter(
    private val usersList: GitUsersRepoImpl,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<UserView>() {

    class ListPresenter : IUsersListPresenter {

        var users = mutableListOf<UsersItem>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
       override val itemClickStream: PublishSubject<UserItemView> = PublishSubject.create()

        override fun bindView(view: UserItemView) {

            val user = users[view.pos]

            Observable.just(user).map {
                it.login
            }.subscribe {
                view.setText(it)
            }
        }

        override fun getCount(): Int = users.size
    }

    val listPresenter = ListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()

        listPresenter.itemClickStream.debounce(300, TimeUnit.MILLISECONDS)
            .switchMap {
                getLoginByPosition(it.pos)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                router.navigateTo(screens.user(it))
            }
    }

    private fun getLoginByPosition(position: Int): Observable<String> =
        Observable.just(listPresenter.users).map {
             it[position].login
        }


    private fun loadData() {
        usersList.getUsers(

            onSuccess = { it ->

                Observable.just(it).flatMap {
                    return@flatMap Observable.fromIterable(it)
                }.subscribe({
                    listPresenter.users.add(it)
                }, {
                    viewState.onError(Throwable("Network Error"))
                })

                viewState.updateList()
            },
            onError = {

                viewState.onError(it)
            }
        )
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}




