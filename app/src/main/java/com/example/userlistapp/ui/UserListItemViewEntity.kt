package com.example.userlistapp.ui

import com.example.userlistapp.data.datasource.Person


class UserListItemViewEntity(private val person: Person) {

    fun getFullName() = person.fullName

    fun getId() = person.id

}