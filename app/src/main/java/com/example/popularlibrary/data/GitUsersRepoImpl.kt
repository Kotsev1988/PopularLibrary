package com.example.popularlibrary.data

import com.example.popularlibrary.data.net.GitUsersAPIClient
import com.example.popularlibrary.domain.UserRepo
import com.example.popularlibrary.domain.Users
import com.example.popularlibrary.domain.UsersItem
import com.example.popularlibrary.domain.repos.ReposItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitUsersRepoImpl(private val gitUsersAPIClient: GitUsersAPIClient) : UserRepo {

    override fun getUsers(): Single<Users> = gitUsersAPIClient.getListOfUsers().subscribeOn(Schedulers.io())

    override fun getUser(
        login: String,
        onSuccess: (UsersItem) -> Unit,
        onError: (Throwable) -> Unit,
    ) {
        gitUsersAPIClient.getUser(login).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                onSuccess(it)
            }, {
                onError(it)
            })
    }

    override fun getUserRepos(
        login: String,
        onSuccess: (List<ReposItem>) -> Unit,
        onError: (Throwable) -> Unit,
    ) {

        gitUsersAPIClient.getUserRepos(login = login)
            .enqueue(object : Callback<List<ReposItem>> {
            override fun onResponse(
                call: Call<List<ReposItem>>,
                response: Response<List<ReposItem>>,
            ) {
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