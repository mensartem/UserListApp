package com.example.userlistapp.di

import com.example.userlistapp.data.repository.UserRepository
import org.koin.core.module.Module
import org.koin.dsl.module


val repositoryModule: Module = module {
    single { UserRepository(get()) }
}

