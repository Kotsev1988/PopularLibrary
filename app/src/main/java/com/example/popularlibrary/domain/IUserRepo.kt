package com.example.popularlibrary.domain

import com.example.popularlibrary.domain.repositories.Repos
import com.example.popularlibrary.domain.users.UsersItem
import io.reactivex.rxjava3.core.Single

interface IUserRepo {
    fun getUsers(): Single<List<UsersItem>>

    fun getUser(
        login: String
    ) : Single<UsersItem>



}