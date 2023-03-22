package com.example.popularlibrary.view.user.presenter

import com.example.popularlibrary.data.GitUsersRepoImpl
import com.example.popularlibrary.domain.repos.ReposItem
import com.example.popularlibrary.view.user.ProfileView
import com.example.popularlibrary.view.user.ReposItemView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPresenter(private val usersList: GitUsersRepoImpl, private val router: Router): MvpPresenter<ProfileView>() {

    class ReposListPresenter() : IUserReposListPresenter {
         val repos = mutableListOf<ReposItem>()
        override var onItemClickListener: ((ReposItemView) -> Unit)? = null

        override fun bindView(view: ReposItemView) {
            val repo: ReposItem = repos[view.pos]

            view.setRepoName(repo.name)
        }

        override fun getCount(): Int = repos.size
    }

    val repoListPresenter = ReposListPresenter()


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        repoListPresenter.onItemClickListener={

        }
    }

     fun loadRepoData(login : String) {
        usersList.getUserRepos(login,
        onSuccess = {
            repoListPresenter.repos.addAll(it)
            viewState.updateList()
        },
        onError = {

            viewState.onError(it)
        })
    }


    fun loadData(login: String){
        usersList.getUser(
            login = login,
            onSuccess = {
                viewState.setName(it.login)
                viewState.setAvatar(it.avatar_url)
            },
            onError = {
                viewState.onError(it)
            }
        )
    }

    fun backPressed(): Boolean{
        router.exit()
        return true
    }

}