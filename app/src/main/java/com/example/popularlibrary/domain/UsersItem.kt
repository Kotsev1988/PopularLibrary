package com.example.popularlibrary.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UsersItem(

    val login: String,
    val id: Int,
    val avatar_url: String,
    val repos_url: String

): Parcelable