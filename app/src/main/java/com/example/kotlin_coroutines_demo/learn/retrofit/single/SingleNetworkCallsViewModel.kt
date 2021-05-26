package com.example.kotlin_coroutines_demo.learn.retrofit.single

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_coroutines_demo.data.api.ApiHelper
import com.example.kotlin_coroutines_demo.data.local.DatabaseHelper
import com.example.kotlin_coroutines_demo.data.model.ApiUser
import com.example.kotlin_coroutines_demo.utils.Resource
import kotlinx.coroutines.launch

class SingleNetworkCallsViewModel(
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
                val userFromApi = apiHelper.getUsers()
                users.postValue(Resource.success(userFromApi))
            } catch (e : Exception) {
                emptyList<ApiUser>()

            }
        }
    }

    fun getUsers() : LiveData<Resource<List<ApiUser>>>{
        return users
    }

}