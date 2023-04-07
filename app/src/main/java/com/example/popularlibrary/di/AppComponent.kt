package com.example.popularlibrary.di

import com.example.popularlibrary.di.modules.*
import com.example.popularlibrary.view.MainActivity
import com.example.popularlibrary.view.MainPresenter
import com.example.popularlibrary.view.user.UserFragment
import com.example.popularlibrary.view.users.UsersFragment
import com.example.popularlibrary.view.users.presenter.UsersPresenter
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

    fun inject(usersFragment: UsersFragment)
    fun inject(userFragment: UserFragment)

}