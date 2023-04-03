package com.example.popularlibrary.data

import com.example.popularlibrary.data.net.GitUsersAPIClient
import com.example.popularlibrary.data.room.Database
import com.example.popularlibrary.data.room.users.entity.RoomGitHubUsers
import com.example.popularlibrary.domain.IUserRepo
import com.example.popularlibrary.domain.users.UsersItem
import com.example.popularlibrary.domain.network.INetworkStatus
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GitUsersRepoImpl(private val gitUsersAPIClient: GitUsersAPIClient, private val networkStatus: INetworkStatus, val db: Database) : IUserRepo {

    override fun getUsers(): Single<List<UsersItem>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline){

            gitUsersAPIClient.getListOfUsers().flatMap { users ->
                Single.fromCallable {
                    val roomUser = users.map { user ->
                        RoomGitHubUsers(user.id, user.login, user.avatar_url, user.repos_url)
                    }
                    db.userDao.insert(roomUser)
                    users
                }
            }
        }else{
            Single.fromCallable {

                db.userDao.getAll().map { roomUser ->
                    UsersItem(roomUser.id, roomUser.login, roomUser.avatar_url, roomUser.repos_url)
                }
            }
        }
    }.subscribeOn(Schedulers.io())


    override fun getUser(login: String): Single<UsersItem> =
        gitUsersAPIClient.getUser(login).subscribeOn(Schedulers.io())



}