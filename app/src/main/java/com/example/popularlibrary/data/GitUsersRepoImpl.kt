package com.example.popularlibrary.data

import com.example.popularlibrary.data.net.GitUsersAPIClient
import com.example.popularlibrary.domain.UserRepo
import com.example.popularlibrary.domain.Users
import com.example.popularlibrary.domain.UsersItem
import com.example.popularlibrary.domain.repos.Repos
import com.example.popularlibrary.domain.repos.ReposItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitUsersRepoImpl(private val gitUsersAPIClient: GitUsersAPIClient) : UserRepo {


    override fun getUsers(onSuccess: (Users) -> Unit, onError: (Throwable) -> Unit) {
        gitUsersAPIClient.getListOfUsers().enqueue(object : Callback<Users> {
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                onError(Throwable("Error"))
            }

        })
    }

    override fun getUser(
        login: String,
        onSuccess: (UsersItem) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        gitUsersAPIClient.getUser(login).enqueue(object : Callback<UsersItem> {

            override fun onResponse(call: Call<UsersItem>, response: Response<UsersItem>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onSuccess(it)
                    }
                }
            }

            override fun onFailure(call: Call<UsersItem>, t: Throwable) {
                onError(Throwable("Error"))
            }

        })
    }

    override fun getUserRepos(
        login: String,
        onSuccess: (List<ReposItem>) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        gitUsersAPIClient.getUserRepos(login = login).enqueue(object : Callback<List<ReposItem>>{
            override fun onResponse(call: Call<List<ReposItem>>, response: Response<List<ReposItem>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onSuccess(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<ReposItem>>, t: Throwable) {
                onError(Throwable("Error"))
            }
        })
    }


}