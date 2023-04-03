package com.example.popularlibrary.domain

import com.example.popularlibrary.data.room.repository.entity.RoomGitHubRepository
import com.example.popularlibrary.data.room.users.entity.RoomGitHubUsers
import com.example.popularlibrary.domain.repositories.Repos
import com.example.popularlibrary.domain.repositories.ReposItem
import com.example.popularlibrary.domain.users.UsersItem
import io.reactivex.rxjava3.core.Single

interface IRepositoryRepo {
    fun getUserRepos(login: UsersItem): Single<List<ReposItem>>
}