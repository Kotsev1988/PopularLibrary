package com.example.popularlibrary.domain

import com.example.popularlibrary.domain.repos.ReposItem
import io.reactivex.rxjava3.core.Single

interface UserRepo {
    fun getUsers(): Single<Users>

    fun getUser(
        login: String,
        onSuccess: (UsersItem) ->Unit,
        onError: (Throwable) ->Unit
    )

    fun getUserRepos(
        login: String,
        onSuccess: (List<ReposItem>) ->Unit,
        onError: (Throwable) ->Unit
    )

}