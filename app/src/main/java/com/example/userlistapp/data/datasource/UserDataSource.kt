package com.example.userlistapp.data.datasource



class UserDataSource(private val dataSource: DataSource) {


    fun fetchUserList(
        next: String?,
        completionHandler: FetchCompletionHandler,
    ) {
        dataSource.fetch(next, completionHandler)
    }

}
