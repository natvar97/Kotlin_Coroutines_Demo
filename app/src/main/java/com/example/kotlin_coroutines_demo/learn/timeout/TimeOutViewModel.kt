package com.example.kotlin_coroutines_demo.learn.timeout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_coroutines_demo.data.api.ApiHelper
import com.example.kotlin_coroutines_demo.data.local.DatabaseHelper
import com.example.kotlin_coroutines_demo.data.model.ApiUser
import com.example.kotlin_coroutines_demo.utils.Resource
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

class TimeOutViewModel(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper
) : ViewModel() {

    private val users = MutableLiveData<Resource<List<ApiUser>>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            users.postValue(Resource.loading(null))
            try {
                withTimeout(100) {
                    val usersFromApi = apiHelper.getUsers()
                    users.postValue(Resource.success(usersFromApi))
                }
            } catch (e: Exception) {
                users.postValue(Resource.error(e.message, null))
            }
        }
    }

    fun getUsers(): LiveData<Resource<List<ApiUser>>> {
        return users
    }

}