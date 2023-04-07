package com.example.popularlibrary.data.net

import com.example.popularlibrary.domain.repositories.ReposItem
import com.example.popularlibrary.domain.users.UsersItem
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitUsersAPI {

    @GET("users")
    fun getUsers(): Single<List<UsersItem>>

    @GET("users/{login}/repos")
    fun getUserRepos(
        @Path("login") userLogin: String,
    ): Single<List<ReposItem>>

}