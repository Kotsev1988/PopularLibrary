package com.example.popularlibrary.view.user.presenter

import com.example.popularlibrary.data.room.Database
import com.example.popularlibrary.domain.IRepositoryRepo
import com.example.popularlibrary.domain.repositories.ReposItem
import com.example.popularlibrary.domain.users.UsersItem
import com.example.popularlibrary.view.user.ProfileView
import com.example.popularlibrary.view.user.ReposItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class UserPresenter(
    val user: UsersItem,
    private val uiScheduler: Scheduler
) :
    MvpPresenter<ProfileView>() {


    @Inject  lateinit var repoList: IRepositoryRepo
    @Inject  lateinit var router: Router

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
        loadData()
        loadRepoData()
        repoListPresenter.onItemClickListener = { it ->

            Observable.just(it)
                .map {
                    Pair(repoListPresenter.repos[it.pos].created_at,
                        repoListPresenter.repos[it.pos].forks_count)
                }
                .subscribe {

                    viewState.setRepoDateOnClick(it.first, it.second)
                }
        }
    }

    fun loadRepoData() {
        repoList.getUserRepos(user)
            .observeOn(uiScheduler)
            .subscribe({ repoList ->
                repoListPresenter.repos.clear()
                repoListPresenter.repos.addAll(repoList)
                viewState.updateList()

            }, {
                viewState.onError(it)
            })
    }

    fun loadData() {
        viewState.setName(user.login)
        viewState.setAvatar(user.avatar_url)

    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}