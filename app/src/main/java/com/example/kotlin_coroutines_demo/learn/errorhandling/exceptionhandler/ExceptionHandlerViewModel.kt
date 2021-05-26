package com.example.kotlin_coroutines_demo.learn.errorhandling.exceptionhandler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_coroutines_demo.data.api.ApiHelper
import com.example.kotlin_coroutines_demo.data.local.DatabaseHelper
import com.example.kotlin_coroutines_demo.data.model.ApiUser
import com.example.kotlin_coroutines_demo.utils.Resource
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class ExceptionHandlerViewModel(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper
) : ViewModel() {

    private val users = MutableLiveData<Resource<List<ApiUser>>>()

    private val handler = CoroutineExceptionHandler { _, exception ->
        users.postValue(Resource.error("Something went wrong ", null))
    }

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch(handler) {
            users.postValue(Resource.loading(null))
            val usersFromApi = apiHelper.getUsers()
            users.postValue(Resource.success(usersFromApi))
        }
    }

    fun getUsers() : LiveData<Resource<List<ApiUser>>>{
        return users
    }

}