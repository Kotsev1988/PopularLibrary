package com.example.popularlibrary.data.room.users.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomGitHubUsers (
    @PrimaryKey var id: String,
    var login: String,
    var avatar_url: String,
    var repos_url: String
)