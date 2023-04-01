package com.example.popularlibrary.domain

import com.example.popularlibrary.domain.repos.Repos
import com.example.popularlibrary.domain.repos.ReposItem
import io.reactivex.rxjava3.core.Single

interface UserRepo {
    fun getUsers(): Single<Users>

    fun getUser(
        login: String
    ) : Single<UsersItem>

    fun getUserRepos(login: String): Single<Repos>

}