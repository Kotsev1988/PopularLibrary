package com.example.popularlibrary.data.room.cache.room

import com.example.popularlibrary.data.room.Database
import com.example.popularlibrary.data.room.cache.IGitHubUserCache
import com.example.popularlibrary.data.room.users.entity.RoomGitHubUsers
import com.example.popularlibrary.domain.users.UsersItem
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomGitHubUserCache(private val db: Database): IGitHubUserCache {

    override fun insertInDatabase(users: List<UsersItem>): Single<List<UsersItem>> = Single.fromCallable {
            val roomUser = users.map { user ->
                RoomGitHubUsers(user.id, user.login, user.avatar_url, user.repos_url)
            }
            db.userDao.insert(roomUser)
            users
        }.subscribeOn(Schedulers.io())


    override fun getCacheFromDatabase(): Single<List<UsersItem>> =  Single.fromCallable {
            db.userDao.getAll().map { roomUser ->
                UsersItem(roomUser.id, roomUser.login, roomUser.avatar_url, roomUser.repos_url)
            }
        }
}