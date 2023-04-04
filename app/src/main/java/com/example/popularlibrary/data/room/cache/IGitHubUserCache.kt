package com.example.popularlibrary.data.room.cache


import com.example.popularlibrary.domain.users.UsersItem
import io.reactivex.rxjava3.core.Single

interface IGitHubUserCache {
    fun insertInDatabase(users: List<UsersItem>): Single<List<UsersItem>>
    fun getCacheFromDatabase(): Single<List<UsersItem>>
}