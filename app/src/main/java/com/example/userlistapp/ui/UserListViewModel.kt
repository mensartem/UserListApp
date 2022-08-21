package com.example.userlistapp.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.userlistapp.base.BaseViewModel
import com.example.userlistapp.data.datasource.FetchResponse
import com.example.userlistapp.data.datasource.Person
import com.example.userlistapp.data.usecase.FetchUserListUseCase
import com.example.userlistapp.util.Resource
import com.example.userlistapp.util.Status
import com.example.userlistapp.util.extension.orFalse


class UserListViewModel(
    application: Application,
    private val fetchUserListUseCase: FetchUserListUseCase,
) : BaseViewModel(application) {

    var next: String? = null

    private var _userList = MutableLiveData<ArrayList<Person>?>()
    val userList: LiveData<ArrayList<Person>?> get() = _userList

    private var _status = MutableLiveData<UserListStatusViewState>()
    val status: LiveData<UserListStatusViewState> get() = _status

    val listArray = ArrayList<Person>()
    val listIdArray = ArrayList<Int>()

    fun fetchUserList(resetList: Boolean) {

        if (resetList) {
            reset()
        }
        fetchUserListUseCase.fetchUserList(
            next,
            resourceHandler = { resource ->
                val viewState = when (resource) {
                    is Resource.Success -> {
                        doSuccessOperation(resource)
                        UserListStatusViewState(Status.Success)
                    }
                    is Resource.Error -> {
                        UserListStatusViewState(Status.Error(resource.exception))
                    }
                    else -> {
                        UserListStatusViewState(Status.Loading)
                    }
                }
                setStatus(viewState)
            })

    }

    private fun doSuccessOperation(resource: Resource.Success<Any>) {
        if (resource.data is FetchResponse) {
            val data = resource.data
            resource.data.people.forEach { person ->
                if (!listIdArray.contains(person.id).orFalse()) {
                    listIdArray.add(person.id)
                    listArray.add(person)
                }
            }
            _userList.postValue(listArray)
            next = data.next
        }
    }

    private fun reset() {
        listArray.clear()
        listIdArray.clear()
        next = null
    }

    fun setStatus(status: UserListStatusViewState) {
        _status.postValue(status)
    }
}