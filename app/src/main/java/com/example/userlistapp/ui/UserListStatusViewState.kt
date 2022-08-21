package com.example.userlistapp.ui

import com.example.userlistapp.util.Status


class UserListStatusViewState(private val status: Status) {

    fun getErrorMessage() =
        if (status is Status.Error) status.exception.message else "Error Occurred!"

    fun isError() = status is Status.Error
}