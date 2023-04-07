package com.example.popularlibrary.di.modules

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.popularlibrary.App
import com.example.popularlibrary.data.room.Database
import com.example.popularlibrary.data.room.cache.IGitHubRepositoriesCache
import com.example.popularlibrary.data.room.cache.IGitHubUserCache
import com.example.popularlibrary.data.room.cache.room.RoomGitHubRepositoriesCache
import com.example.popularlibrary.data.room.cache.room.RoomGitHubUserCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun database(app: App) = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()

    @Singleton
    @Provides
    fun userCache(database: Database): IGitHubUserCache = RoomGitHubUserCache(database)

    @Singleton
    @Provides
    fun repositoriesCache(database: Database): IGitHubRepositoriesCache = RoomGitHubRepositoriesCache(database)

}