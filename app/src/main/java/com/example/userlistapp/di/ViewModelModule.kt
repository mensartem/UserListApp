package com.example.userlistapp.di

import com.example.userlistapp.MainViewModel
import com.example.userlistapp.ui.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel { UserListViewModel(get(), get()) }
    viewModel { MainViewModel(get())}

}