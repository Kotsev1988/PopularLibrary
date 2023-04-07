package com.example.popularlibrary.view

import android.os.Bundle
import com.example.popularlibrary.App
import com.example.popularlibrary.R
import com.example.popularlibrary.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.NavigatorHolder

import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    @Inject lateinit var navigatorHolder: NavigatorHolder

    private val navigator = AppNavigator(this, R.id.container)


    private val presenter: MainPresenter by moxyPresenter {
        MainPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        App.instance.appComponent.inject(this)

    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()

    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach { it ->

            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }

}