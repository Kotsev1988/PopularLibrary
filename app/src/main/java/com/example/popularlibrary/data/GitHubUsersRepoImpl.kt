package com.example.popularlibrary.data

import com.example.popularlibrary.data.net.GitUsersAPIClient
import com.example.popularlibrary.data.room.Database
import com.example.popularlibrary.data.room.repository.entity.RoomGitHubRepository
import com.example.popularlibrary.data.room.users.entity.RoomGitHubUsers
import com.example.popularlibrary.domain.IRepositoryRepo
import com.example.popularlibrary.domain.network.INetworkStatus
import com.example.popularlibrary.domain.repositories.ReposItem
import com.example.popularlibrary.domain.users.UsersItem
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GitHubUsersRepoImpl (private val gitUsersAPIClient: GitUsersAPIClient, val networkStatus: INetworkStatus, val db: Database): IRepositoryRepo {

    override fun getUserRepos(login: UsersItem): Single<List<ReposItem>> = networkStatus.isOnlineSingle().flatMap { isOnline ->

    if (isOnline){
        gitUsersAPIClient.getUserRepos(login = login.login).flatMap { repositories ->
            Single.fromCallable {
                val roomUser = login.login.let {
                    db.userDao.findByLogin(it)
                } ?: throw RuntimeException("No such User")

                val roomRepos = repositories.map {
                    RoomGitHubRepository(it.id, it.name, it.created_at, it.forks_count, roomUser.id)
                }
                db.repositoryDao.insert(roomRepos)
                repositories
            }
        }
    }else{
        Single.fromCallable {
            val user = login.login.let {
                db.userDao.findByLogin(it)
            }

            db.repositoryDao.findForUser(user.id).map { repository ->
                ReposItem(repository.id, repository.name, repository.created_at, repository.forks_count)
            }
        }
    }
    }.subscribeOn(Schedulers.io())




}