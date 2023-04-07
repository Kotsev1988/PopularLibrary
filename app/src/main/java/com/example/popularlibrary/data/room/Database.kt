package com.example.popularlibrary.data.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.popularlibrary.data.room.repository.dao.RepositoryDao
import com.example.popularlibrary.data.room.repository.entity.RoomGitHubRepository
import com.example.popularlibrary.data.room.users.entity.RoomGitHubUsers
import com.example.popularlibrary.data.room.users.dao.UserDao

@androidx.room.Database(entities = [RoomGitHubUsers::class, RoomGitHubRepository::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object{
         const val  DB_NAME = "database.db"

    }
}