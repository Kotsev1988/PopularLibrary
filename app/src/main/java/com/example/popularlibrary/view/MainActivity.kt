package com.example.popularlibrary.view


import android.os.Bundle
import com.example.popularlibrary.App
import com.example.popularlibrary.R
import com.example.popularlibrary.databinding.ActivityMainBinding
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {


    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!


    private val navigator = AppNavigator(this, R.id.container)
    private val presenter: MainPresenter by moxyPresenter { MainPresenter(App.instance.router, AndroidScreens()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()

    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {

            if (it is BackButtonListener && it.backPressed()){
                return
            }
        }
        presenter.backClicked()
    }

}