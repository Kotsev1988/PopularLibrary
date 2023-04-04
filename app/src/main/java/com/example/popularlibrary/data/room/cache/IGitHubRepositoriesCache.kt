package com.example.popularlibrary.data.room.cache


import com.example.popularlibrary.domain.repositories.ReposItem
import com.example.popularlibrary.domain.users.UsersItem
import io.reactivex.rxjava3.core.Single

interface IGitHubRepositoriesCache {

    fun insertsRepositoryToDB(login : UsersItem, repositories: List<ReposItem>): Single<List<ReposItem>>
    fun getRepositoriesFromcache(login: UsersItem): Single<List<ReposItem>>
}