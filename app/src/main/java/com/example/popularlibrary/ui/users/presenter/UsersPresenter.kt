package com.example.popularlibrary.ui.users.presenter

import com.example.popularlibrary.domain.IUserRepo
import com.example.popularlibrary.domain.users.UsersItem
import com.example.popularlibrary.ui.screens.IScreens
import com.example.popularlibrary.ui.users.UserItemView
import com.example.popularlibrary.ui.users.UserView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import moxy.MvpPresenter
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class UsersPresenter(
    private val uiObserve: Scheduler
) : MvpPresenter<UserView>() {

    @Inject
    lateinit var usersList: IUserRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens


    class ListPresenter : IUsersListPresenter {

        var users = mutableListOf<UsersItem>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override val itemClickStream: PublishSubject<UserItemView> = PublishSubject.create()

        override fun bindView(view: UserItemView) {

            val user = users[view.pos]

            user.avatar_url.let { view.loadAvatar(it) }
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
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                val user = listPresenter.users[it.pos]
                router.navigateTo(screens.user(user))
            }
    }

     fun loadData() {


         usersList.getUsers()
            .observeOn(uiObserve).subscribe(
                { repos ->
                    listPresenter.users.clear()
                    listPresenter.users.addAll(repos)
                    viewState.updateList()

                }, {
                    viewState.onError(it)
                })
    }


    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}




