package com.example.popularlibrary.view.users.presenter

import com.example.popularlibrary.data.GitUsersRepoImpl
import com.example.popularlibrary.domain.users.UsersItem
import com.example.popularlibrary.view.screens.IScreens
import com.example.popularlibrary.view.users.UserItemView
import com.example.popularlibrary.view.users.UserView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import moxy.InjectViewState
import moxy.MvpPresenter
import java.util.concurrent.TimeUnit

@InjectViewState
class UsersPresenter(
    private val usersList: GitUsersRepoImpl,
    private val uiObserve: Scheduler,
    private val router: Router,
    private val screens: IScreens,
) : MvpPresenter<UserView>() {
    private lateinit var disposable: Disposable

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

    private fun loadData() {

        disposable = usersList.getUsers()
            .observeOn(uiObserve).subscribe(
                { repos ->
                    listPresenter.users.clear()
                    listPresenter.users.addAll(repos)
                    viewState.updateList()

                }, {
                    viewState.onError(it)
                })

    }

    fun destroyView() {
        disposable.dispose()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}




