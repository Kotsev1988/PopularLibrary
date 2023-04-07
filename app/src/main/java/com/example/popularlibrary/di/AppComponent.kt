package com.example.popularlibrary.di

import com.example.popularlibrary.di.modules.*
import com.example.popularlibrary.ui.MainActivity
import com.example.popularlibrary.ui.MainPresenter
import com.example.popularlibrary.ui.user.presenter.UserPresenter
import com.example.popularlibrary.ui.users.presenter.UsersPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ApiModule::class,
        CiceroneModule::class,
        DatabaseModule::class,
        RepoModule::class
    ]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userPresenter: UserPresenter)



}