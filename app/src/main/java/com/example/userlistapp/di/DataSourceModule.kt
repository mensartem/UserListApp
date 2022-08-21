package com.example.userlistapp.di

import com.example.userlistapp.data.datasource.DataSource
import com.example.userlistapp.data.datasource.UserDataSource
import org.koin.core.module.Module
import org.koin.dsl.module

val dataSourceModule: Module = module {
    single { UserDataSource(get()) }
    single { DataSource() }
}
