package com.example.popularlibrary.data

import com.example.popularlibrary.data.net.GitUsersAPI
import com.example.popularlibrary.data.room.cache.IGitHubRepositoriesCache
import com.example.popularlibrary.domain.IRepositoryRepo
import com.example.popularlibrary.domain.network.INetworkStatus
import com.example.popularlibrary.domain.repositories.ReposItem
import com.example.popularlibrary.domain.users.UsersItem
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GitHubRepositoriesImpl(
    private val gitUsersAPIClient: GitUsersAPI,
    private val networkStatus: INetworkStatus,
    private val cache: IGitHubRepositoriesCache,
) : IRepositoryRepo {

    override fun getUserRepos(login: UsersItem): Single<List<ReposItem>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->

            if (isOnline) {
                gitUsersAPIClient.getUserRepos(login.login).flatMap { repositories ->

                    cache.insertsRepositoryToDB(login, repositories).map {
                        it
                    }
                }
            } else {
                cache.getRepositoriesFromcache(login)
            }
        }.subscribeOn(Schedulers.io())
}