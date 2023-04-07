package com.example.popularlibrary.di.modules

import com.example.popularlibrary.App
import com.example.popularlibrary.data.net.GitUsersAPI
import com.example.popularlibrary.domain.network.INetworkStatus
import com.example.popularlibrary.ui.network.NetworkStatusImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Named("baseUrl")
    @Provides
    fun baseUrl(): String = "https://api.github.com/"

    @Singleton
    @Provides
    fun api(@Named("baseUrl") baseUrl: String, gson: Gson): GitUsersAPI {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(GitUsersAPI::class.java)
    }

    @Singleton
    @Provides
    fun gson(): Gson = GsonBuilder().setLenient().create()

//        GsonBuilder()
//        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//        .excludeFieldsWithoutExposeAnnotation()
//        .create()

    @Singleton
    @Provides
    fun networkStatus(app: App): INetworkStatus = NetworkStatusImpl(app)

}