package com.example.popularlibrary.data.net

import com.example.popularlibrary.data.room.users.entity.RoomGitHubUsers
import com.example.popularlibrary.domain.users.UsersItem
import com.example.popularlibrary.domain.repositories.Repos
import com.example.popularlibrary.domain.repositories.ReposItem
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitUsersAPI {

    @GET("users")
    fun getUsers(): Single<List<UsersItem>>

    @GET("users/{login}")
    fun getUser(
        @Path("login") userLogin: String,
    ): Single<UsersItem>

    @GET("users/{login}/repos")
    fun getUserRepos(
        @Path("login") userLogin: String,
    ): Single<List<ReposItem>>

}