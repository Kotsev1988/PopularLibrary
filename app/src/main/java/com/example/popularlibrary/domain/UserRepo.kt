package com.example.popularlibrary.domain

import com.example.popularlibrary.domain.repos.Repos
import com.example.popularlibrary.domain.repos.ReposItem

interface UserRepo {
    fun getUsers(
        onSuccess: (Users) ->Unit,
        onError: (Throwable) ->Unit
    )

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