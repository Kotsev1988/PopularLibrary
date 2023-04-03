package com.example.popularlibrary.domain.users

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UsersItem(

    val id: String,
    val login: String,
    val avatar_url: String,
    val repos_url: String

): Parcelable