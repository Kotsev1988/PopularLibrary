package com.example.popularlibrary.data.net

import com.example.popularlibrary.domain.Users
import com.example.popularlibrary.domain.UsersItem
import com.example.popularlibrary.domain.repos.Repos
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import java.io.IOException

class GitUsersAPIClient {
    private val baseURL = "https://api.github.com/"

    private fun apiGitRetrofit(baseURL: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(
                GsonBuilder().setLenient().create()
            ))
            .client(createOkHttpClient(GitApiInterceptor()))
            .build()
    }

    private val serviceApi: GitUsersAPI by lazy {
        return@lazy apiGitRetrofit(baseURL).create(GitUsersAPI::class.java)
    }

    fun getListOfUsers(): Single<Users> = serviceApi.getUsers()


    fun getUser(login: String): Single<UsersItem> = serviceApi.getUser(login)


    fun getUserRepos(login: String): Single<Repos> = serviceApi.getUserRepos(login)


    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

        return httpClient.build()
    }

    inner class GitApiInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            return chain.proceed(chain.request())
        }
    }
}