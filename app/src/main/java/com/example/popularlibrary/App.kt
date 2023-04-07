package com.example.popularlibrary

import android.app.Application
import com.example.popularlibrary.di.AppComponent
import com.example.popularlibrary.di.DaggerAppComponent
import com.example.popularlibrary.di.modules.AppModule

class App: Application() {

    companion object {
        lateinit var instance: App
    }
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}