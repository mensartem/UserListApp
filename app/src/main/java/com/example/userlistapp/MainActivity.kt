package com.example.userlistapp



import com.example.userlistapp.base.BaseActivity
import com.example.userlistapp.databinding.ActivityMainBinding


class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(MainViewModel::class) {

    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun initialize() {
    }
}