package com.example.popularlibrary.data.room.repository.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.popularlibrary.data.room.users.entity.RoomGitHubUsers

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGitHubUsers::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
class RoomGitHubRepository (

    @PrimaryKey var id: String,
    var name: String,
    var created_at: String,
    var forks_count: Int,
    var userId: String

    )