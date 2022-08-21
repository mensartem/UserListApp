package com.example.userlistapp.data.repository

import com.example.userlistapp.data.datasource.UserDataSource
import com.example.userlistapp.util.Resource

typealias ResourceHandler = (Resource<Any>?) -> Unit


class UserRepository(
    private val dataSource: UserDataSource,
) {



    fun fetchUserList(next: String?, resourceHandler: ResourceHandler) {
        resourceHandler(Resource.Loading)
        dataSource.fetchUserList(next, completionHandler = { fetchResponse, fetchError ->

            if (fetchResponse == null) {
                resourceHandler(Resource.Error(Exception(fetchError?.errorDescription)))
            } else {
                resourceHandler(Resource.Success(fetchResponse))
            }
        })

    }

}