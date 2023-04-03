package com.example.popularlibrary.data.room.users.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.popularlibrary.data.room.users.entity.RoomGitHubUsers

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomGitHubUsers)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: RoomGitHubUsers)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomGitHubUsers>)

    @Update
    fun update(user: RoomGitHubUsers)

    @Update
    fun update(vararg users: RoomGitHubUsers)

    @Update
    fun update(user: List<RoomGitHubUsers>)

    @Delete
    fun delete(user: RoomGitHubUsers)

    @Delete
    fun delete(vararg users: RoomGitHubUsers)

    @Delete
    fun delete(user: List<RoomGitHubUsers>)

    @Query("SELECT * FROM RoomGitHubUsers")
    fun getAll(): List<RoomGitHubUsers>

    @Query("SELECT * FROM RoomGitHubUsers WHERE login = :login LIMIT 1")
    fun findByLogin(login: String): RoomGitHubUsers

}