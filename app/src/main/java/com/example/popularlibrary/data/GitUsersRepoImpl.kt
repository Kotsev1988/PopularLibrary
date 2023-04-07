package com.example.popularlibrary.data

import com.example.popularlibrary.data.net.GitUsersAPI
import com.example.popularlibrary.data.room.cache.IGitHubUserCache
import com.example.popularlibrary.domain.IUserRepo
import com.example.popularlibrary.domain.users.UsersItem
import com.example.popularlibrary.domain.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GitUsersRepoImpl(
    private val api: GitUsersAPI,
    private val networkStatus: INetworkStatus,
    private val cacheUsers: IGitHubUserCache
) : IUserRepo {

    override fun getUsers(): Single<List<UsersItem>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->

            if (isOnline) {

                api.getUsers().flatMap { users ->

                    cacheUsers.insertInDatabase(users).map {
                        it
                    }
                }
            } else {
                cacheUsers.getCacheFromDatabase()
            }
        }.subscribeOn(Schedulers.io())
}