package com.example.userlistapp.data.usecase

import com.example.userlistapp.data.repository.ResourceHandler
import com.example.userlistapp.data.repository.UserRepository

class FetchUserListUseCase(
    private val repository: UserRepository
) {
    fun fetchUserList(next: String?, resourceHandler: ResourceHandler) {
        return repository.fetchUserList(next, resourceHandler)
    }




}