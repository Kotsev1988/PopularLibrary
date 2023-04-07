package com.example.popularlibrary.di.modules

import com.example.popularlibrary.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {

    @Provides
    fun app() : App = app

}