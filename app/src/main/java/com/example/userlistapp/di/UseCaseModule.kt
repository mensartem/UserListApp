package com.example.userlistapp.di

import com.example.userlistapp.data.usecase.FetchUserListUseCase
import org.koin.core.module.Module
import org.koin.dsl.module


val useCaseModule: Module = module {
    single { FetchUserListUseCase(get()) }

}