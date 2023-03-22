package com.example.popularlibrary.data.net


import com.example.popularlibrary.domain.Users
import com.example.popularlibrary.domain.UsersItem
import com.example.popularlibrary.domain.repos.Repos
import com.example.popularlibrary.domain.repos.ReposItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitUsersAPI {

    @GET("users")
    fun getUsers(): Call<Users>

    @GET("users/{login}")
    fun getUser(
        @Path("login") userLogin: String
    ): Call<UsersItem>

    @GET("users/{login}/repos")
    fun getUserRepos(
        @Path("login") userLogin: String
    ): Call<List<ReposItem>>


}