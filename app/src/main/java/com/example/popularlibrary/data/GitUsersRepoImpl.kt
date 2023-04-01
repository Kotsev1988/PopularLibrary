package com.example.popularlibrary.data

import com.example.popularlibrary.data.net.GitUsersAPIClient
import com.example.popularlibrary.domain.UserRepo
import com.example.popularlibrary.domain.Users
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GitUsersRepoImpl(private val gitUsersAPIClient: GitUsersAPIClient) : UserRepo {

    override fun getUsers(): Single<Users> =
        gitUsersAPIClient.getListOfUsers().subscribeOn(Schedulers.io())

    override fun getUser(login: String) =
        gitUsersAPIClient.getUser(login).subscribeOn(Schedulers.io())

    override fun getUserRepos(login: String) =
        gitUsersAPIClient.getUserRepos(login = login).subscribeOn(Schedulers.io())

}