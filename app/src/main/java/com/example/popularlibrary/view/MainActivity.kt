package com.example.popularlibrary.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import com.example.popularlibrary.databinding.ActivityMainBinding
import com.example.popularlibrary.presenter.MainPresenter

class MainActivity : AppCompatActivity() , MainView {


    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnCounter1.setOnClickListener{presenter.counterClickFirst()}
        binding.btnCounter2.setOnClickListener{presenter.counterClickSecond()}
        binding.btnCounter3.setOnClickListener{presenter.counterClickThird()}

    }

    override fun setFirstButton(text: String){
        binding.btnCounter1.text = text
    }

    override fun setSecondButton(text: String){
        binding.btnCounter2.text = text
    }

    override fun setThirdButton(text: String){
        binding.btnCounter3.text = text
    }
}