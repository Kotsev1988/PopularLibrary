package com.example.popularlibrary.view.users.presenter

import com.example.popularlibrary.data.GitUsersRepoImpl
import com.example.popularlibrary.domain.UsersItem
import com.example.popularlibrary.view.AndroidScreens
import com.example.popularlibrary.view.users.UserItemView
import com.example.popularlibrary.view.users.UserView
import com.github.terrakok.cicerone.Router
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class UsersPresenter (
    private val usersList: GitUsersRepoImpl,
    private val router: Router
    ) : MvpPresenter<UserView>() {

    class ListPresenter : IUsersListPresenter {

        var users = mutableListOf<UsersItem>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun bindView(view: UserItemView) {

            val user = users[view.pos]
            view.setText(user.login)
        }

        override fun getCount(): Int = users.size
    }

    val listPresenter = ListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        listPresenter.itemClickListener = {
            router.navigateTo(AndroidScreens().user(listPresenter.users[it.pos].login))
        }
    }

    private fun loadData() {
        usersList.getUsers(

            onSuccess = {
                listPresenter.users.addAll(it)
                viewState.updateList()
            },
            onError = {

            }

        )

    }

    fun backPressed(): Boolean{
        router.exit()
        return true
    }
}