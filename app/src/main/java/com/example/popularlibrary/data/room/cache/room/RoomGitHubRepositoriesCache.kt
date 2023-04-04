package com.example.popularlibrary.data.room.cache.room

import com.example.popularlibrary.data.room.Database
import com.example.popularlibrary.data.room.cache.IGitHubRepositoriesCache
import com.example.popularlibrary.data.room.repository.entity.RoomGitHubRepository
import com.example.popularlibrary.domain.repositories.ReposItem
import com.example.popularlibrary.domain.users.UsersItem
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomGitHubRepositoriesCache(val db: Database): IGitHubRepositoriesCache {
    override fun insertsRepositoryToDB(login : UsersItem, repositories: List<ReposItem>): Single<List<ReposItem>>  =  Single.fromCallable {

            val roomUser = login.login.let {
                db.userDao.findByLogin(it)
            } ?: throw RuntimeException("No such User")

            val roomRepos = repositories.map {
                RoomGitHubRepository(it.id, it.name, it.created_at, it.forks_count, roomUser.id)
            }
            db.repositoryDao.insert(roomRepos)
            repositories
        }.subscribeOn(Schedulers.io())


    override fun getRepositoriesFromcache(login: UsersItem): Single<List<ReposItem>> = Single.fromCallable {
                    val user = login.login.let {
                        db.userDao.findByLogin(it)
                    }

                  return@fromCallable  db.repositoryDao.findForUser(user.id).map { repository ->
                        ReposItem(repository.id,
                            repository.name,
                            repository.created_at,
                            repository.forks_count)
                    }
                }.subscribeOn(Schedulers.io())

}