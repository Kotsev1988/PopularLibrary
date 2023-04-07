package com.example.popularlibrary.di.modules

import com.example.popularlibrary.view.AndroidScreens
import com.example.popularlibrary.view.screens.IScreens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CiceroneModule {

     var cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    fun cicerone(): Cicerone<Router> = cicerone

    @Singleton
    @Provides
    fun navigateHolder() = cicerone.getNavigatorHolder()

    @Singleton
    @Provides
    fun router() = cicerone.router
    @Singleton
    @Provides
    fun screens(): IScreens = AndroidScreens()

}