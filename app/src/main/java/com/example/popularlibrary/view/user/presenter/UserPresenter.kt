package com.example.popularlibrary.view.user.presenter

import com.example.popularlibrary.data.GitUsersRepoImpl
import com.example.popularlibrary.domain.repos.ReposItem
import com.example.popularlibrary.view.user.ProfileView
import com.example.popularlibrary.view.user.ReposItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter

class UserPresenter(
    private val usersList: GitUsersRepoImpl,
    val uiScheduler: Scheduler,
    private val router: Router,
) :
    MvpPresenter<ProfileView>() {

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
        repoListPresenter.onItemClickListener = { it ->

            Observable.just(it)
                .map {
                    Pair(repoListPresenter.repos[it.pos].created_at,
                        repoListPresenter.repos[it.pos].forks)
                }
                .subscribe {

                    viewState.setRepoDateOnClick(it.first, it.second)
                }
        }
    }

    fun loadRepoData(login: String) {
        usersList.getUserRepos(login = login)
            .observeOn(uiScheduler)
            .subscribe({ repoList ->
                repoListPresenter.repos.clear()
                repoListPresenter.repos.addAll(repoList)
                viewState.updateList()

            }, {
                viewState.onError(it)
            })
    }

    fun loadData(login: String) {
        usersList.getUser(login = login)
            .observeOn(uiScheduler)
            .subscribe(
                { repo ->
                    viewState.setName(repo.login)
                    viewState.setAvatar(repo.avatar_url)
                }, {
                    viewState.onError(it)
                })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}