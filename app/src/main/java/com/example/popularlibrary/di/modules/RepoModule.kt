package com.example.popularlibrary.di.modules

import com.example.popularlibrary.data.GitHubRepositoriesImpl
import com.example.popularlibrary.data.GitUsersRepoImpl
import com.example.popularlibrary.data.net.GitUsersAPI
import com.example.popularlibrary.data.room.cache.IGitHubRepositoriesCache
import com.example.popularlibrary.data.room.cache.IGitHubUserCache
import com.example.popularlibrary.domain.IRepositoryRepo
import com.example.popularlibrary.domain.IUserRepo
import com.example.popularlibrary.domain.network.INetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(
        api: GitUsersAPI,
        networkStatus: INetworkStatus,
        cache: IGitHubUserCache,
    ): IUserRepo = GitUsersRepoImpl(api, networkStatus, cache)


    @Singleton
    @Provides
    fun repositoriesRepo(
        api: GitUsersAPI,
        networkStatus: INetworkStatus,
        cache: IGitHubRepositoriesCache,
    ): IRepositoryRepo = GitHubRepositoriesImpl(api, networkStatus, cache)
}