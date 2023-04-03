package com.example.popularlibrary.domain.repositories

data class ReposItem(
    val id: String,
    val name: String,
    val created_at: String,
    val forks_count: Int
)